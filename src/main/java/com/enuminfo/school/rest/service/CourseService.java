/**
 * 
 */
package com.enuminfo.school.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.school.hibernate.model.Course;
import com.enuminfo.school.hibernate.repository.CourseRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/course")
public class CourseService {

	@Autowired CourseRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Course> getAllCourses() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveCourse(@RequestBody Course course) {
		repository.save(course);
	}
	
	@RequestMapping(value = "/{courseId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCourse(@PathVariable Integer courseId) {
		Course course = repository.findOne(courseId);
		repository.delete(course);
	}
}
