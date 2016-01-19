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
import com.enuminfo.school.hibernate.model.CourseSubject;
import com.enuminfo.school.hibernate.model.Subject;
import com.enuminfo.school.hibernate.repository.CourseRepository;
import com.enuminfo.school.hibernate.repository.CourseSubjectRepository;
import com.enuminfo.school.hibernate.repository.SubjectRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/course")
public class CourseService {

	@Autowired CourseRepository repository;
	@Autowired SubjectRepository subjectRepository;
	@Autowired CourseSubjectRepository courseSubjectRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Course> getAllCourses() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveCourse(@RequestBody Course course) {
		List<String> courseSubjectsFromScreen = new ArrayList<String>();
		for (Iterator<Subject> iterator = course.getSubjects().iterator(); iterator.hasNext();) {
			Subject subject = iterator.next();
			courseSubjectsFromScreen.add(subject.getSubjectName());
		}
		List<Subject> courseSubjectsFromDB = (List<Subject>) subjectRepository.findAll(courseSubjectRepository.findByCourse(course.getCourseId()));
		Course savedCourse = null;
		if (courseSubjectsFromScreen.size() > courseSubjectsFromDB.size()) { // adding new subject for course
			savedCourse = repository.save(course);
			for (Iterator<Subject> iterator = course.getSubjects().iterator(); iterator.hasNext();) {
				Subject subject = iterator.next();
				if (subject.getSubjectId() == 0) {
					CourseSubject courseSubject = new CourseSubject();
					courseSubject.setCourse(savedCourse);
					courseSubject.setSubject(subjectRepository.findBySubjectName(subject.getSubjectName()));
					courseSubjectRepository.save(courseSubject);
				}
			}
		} else if (courseSubjectsFromScreen.size() <= courseSubjectsFromDB.size()) { // removing existing subject & adding new subject for course
			savedCourse = repository.save(course);
			for (Iterator<Subject> iterator = courseSubjectsFromDB.iterator(); iterator.hasNext();) {
				Subject subject = iterator.next();
				if (!courseSubjectsFromScreen.contains(subject.getSubjectName())) {
					CourseSubject courseSubject = courseSubjectRepository.findByCourseAndSubject(course.getCourseId(), subjectRepository.findBySubjectName(subject.getSubjectName()).getSubjectId());
					this.courseSubjectRepository.delete(courseSubject);
				}
			}
			for (Iterator<Subject> iterator = course.getSubjects().iterator(); iterator.hasNext();) {
				Subject subject = iterator.next();
				if (subject.getSubjectId() == 0) {
					CourseSubject courseSubject = new CourseSubject();
					courseSubject.setCourse(savedCourse);
					courseSubject.setSubject(subjectRepository.findBySubjectName(subject.getSubjectName()));
					courseSubjectRepository.save(courseSubject);
				}
			}
		}
	}
}
