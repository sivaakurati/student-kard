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

import com.enuminfo.school.hibernate.model.Student;
import com.enuminfo.school.hibernate.repository.BatchRepository;
import com.enuminfo.school.hibernate.repository.CourseRepository;
import com.enuminfo.school.hibernate.repository.StudentRepsitory;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/student")
public class StudentService {

	@Autowired StudentRepsitory repository;
	@Autowired BatchRepository batchRepository;
	@Autowired CourseRepository courseRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Student> getAllStudents() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveStudent(@RequestBody Student student) {
		repository.save(student);
	}
	
	@RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable Integer studentId) {
		Student student = repository.findOne(studentId);
		repository.delete(student);
	}
	
	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(@PathVariable Integer studentId) {
		Student student = new Student();
		if (studentId != 0) student = repository.findOne(studentId);
		return student;
	}
	
	@RequestMapping(value = "/batch/{batchId}/course/{courseId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Student> getAllStudentsByBatchNCourse(@PathVariable Integer batchId, @PathVariable Integer courseId) {
		return repository.findByBatchAndCourse(batchRepository.findOne(batchId), courseRepository.findOne(courseId));
	}
}
