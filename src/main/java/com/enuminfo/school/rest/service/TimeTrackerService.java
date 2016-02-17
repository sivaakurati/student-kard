/**
 * 
 */
package com.enuminfo.school.rest.service;

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
	public Iterable<TimeTracker> getAllTimeTrackers() {
		return repository.findAll();
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
