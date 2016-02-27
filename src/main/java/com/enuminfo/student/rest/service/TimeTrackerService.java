/**
 * 
 */
package com.enuminfo.student.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.Course;
import com.enuminfo.student.hibernate.model.Subject;
import com.enuminfo.student.hibernate.model.Teacher;
import com.enuminfo.student.hibernate.model.TimeTracker;
import com.enuminfo.student.hibernate.model.TimeTrackerEvent;
import com.enuminfo.student.hibernate.repository.CourseRepository;
import com.enuminfo.student.hibernate.repository.SubjectRepository;
import com.enuminfo.student.hibernate.repository.TeacherRepository;
import com.enuminfo.student.hibernate.repository.TimeTrackerEventRepository;
import com.enuminfo.student.hibernate.repository.TimeTrackerRepository;
import com.enuminfo.student.util.DateTimeUtil;

/**
 * @author Kumar
 */
@RestController
@RequestMapping (value = "/timetracker")
public class TimeTrackerService {

	
	@Autowired TimeTrackerRepository repository;
	@Autowired TimeTrackerEventRepository eventRepository;
	@Autowired CourseRepository courseRepository;
	@Autowired TeacherRepository teacherRepository;
	@Autowired SubjectRepository subjectRepository;
	
	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<TimeTrackerEvent> getAllTimeTrackerEvents() {
		return eventRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveTimetracker(@RequestBody TimeTracker timeTracker) {
		String tempStart = timeTracker.getTempStart();
		String tempEnd = timeTracker.getTempEnd();
		Course course = courseRepository.findOne(timeTracker.getCourse().getCourseId());
		Teacher teacher = teacherRepository.findOne(timeTracker.getTeacher().getTeacherId());
		Subject subject = subjectRepository.findOne(timeTracker.getSubject().getSubjectId());
		timeTracker.setCourse(course);
		timeTracker.setTeacher(teacher);
		timeTracker.setSubject(subject);
		if (timeTracker.getFullDay() == null) timeTracker.setFullDay(false);
		else timeTracker.setFullDay(true);
		System.out.println(DateTimeUtil.convertGMT2ISTDateTime(tempStart));
		System.out.println(DateTimeUtil.convertGMT2ISTDateTime(tempEnd));
		timeTracker.setStartDate(DateTimeUtil.convertGMT2ISTDateTime(tempStart));
		timeTracker.setEndDate(DateTimeUtil.convertGMT2ISTDateTime(tempEnd));
		repository.save(timeTracker);		
		TimeTrackerEvent event = new TimeTrackerEvent();
		event.setTitle(timeTracker.getCourse().getCourseName() + " - " + timeTracker.getTeacher().getTeacherName() + " - " + timeTracker.getSubject().getSubjectName());
		System.out.println(DateTimeUtil.convertGMT2ISTDateTimestamp(tempStart));
		System.out.println(DateTimeUtil.convertGMT2ISTDateTimestamp(tempEnd));
		event.setStart(DateTimeUtil.convertGMT2ISTDateTimestamp(tempStart));
		event.setEnd(DateTimeUtil.convertGMT2ISTDateTimestamp(tempEnd));
		event.setAllDay(timeTracker.getFullDay());
		eventRepository.save(event);
	}
}
