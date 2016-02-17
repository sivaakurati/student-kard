/**
 * 
 */
package com.enuminfo.school.rest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.school.hibernate.model.Course;
import com.enuminfo.school.hibernate.model.Subject;
import com.enuminfo.school.hibernate.model.Teacher;
import com.enuminfo.school.hibernate.model.TimeTracker;
import com.enuminfo.school.hibernate.model.TimeTrackerEvent;
import com.enuminfo.school.hibernate.repository.CourseRepository;
import com.enuminfo.school.hibernate.repository.SubjectRepository;
import com.enuminfo.school.hibernate.repository.TeacherRepository;
import com.enuminfo.school.hibernate.repository.TimeTrackerRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping (value = "/timetracker")
public class TimeTrackerService {

	
	@Autowired TimeTrackerRepository repository;
	@Autowired CourseRepository courseRepository;
	@Autowired TeacherRepository teacherRepository;
	@Autowired SubjectRepository subjectRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TimeTrackerEvent> getAllTimeTrackers() {
		List<TimeTrackerEvent> events = new ArrayList<TimeTrackerEvent>();
		Iterable<TimeTracker> timeTrackers = repository.findAll();
		for (Iterator<TimeTracker> iterator = timeTrackers.iterator(); iterator.hasNext();) {
			TimeTracker timeTracker = (TimeTracker) iterator.next();
			TimeTrackerEvent event = new TimeTrackerEvent();
			event.setTitle(timeTracker.getCourse().getCourseName() + " - " + timeTracker.getTeacher().getTeacherName() + " - " + timeTracker.getSubject().getSubjectName());
			if (timeTracker.getStartDate() != null) {
				String start[] = timeTracker.getStartDate().split("-");
				String startDate = start[0] + "-" + String.valueOf(Integer.parseInt(start[1]) - 1) + "-" + start[2];
				if (timeTracker.getStartTime() != null) startDate = startDate + "T" + timeTracker.getStartTime() + ":00+05:30";
				event.setStart(startDate);
			}
			if (timeTracker.getEndDate() != null) {
				String end[] = timeTracker.getEndDate().split("-");
				String endDate = end[0] + "-" + String.valueOf(Integer.parseInt(end[1]) - 1) + "-" + end[2];
				if (timeTracker.getEndTime() != null) endDate = endDate + "T" + timeTracker.getEndTime() + ":00+05:30";
				event.setEnd(endDate);
			}
			event.setAllDay(timeTracker.getFullDay());
			events.add(event);
		}
		return events;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveTimetracker(@RequestBody TimeTracker timeTracker) {
		Course course = courseRepository.findOne(timeTracker.getCourse().getCourseId());
		Teacher teacher = teacherRepository.findOne(timeTracker.getTeacher().getTeacherId());
		Subject subject = subjectRepository.findOne(timeTracker.getSubject().getSubjectId());
		timeTracker.setCourse(course);
		timeTracker.setTeacher(teacher);
		timeTracker.setSubject(subject);
		if (timeTracker.getFullDay() == null) timeTracker.setFullDay(false);
		else timeTracker.setFullDay(true);
		repository.save(timeTracker);
	}
}
