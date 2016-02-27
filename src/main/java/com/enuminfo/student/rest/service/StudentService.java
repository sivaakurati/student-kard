/**
 * 
 */
package com.enuminfo.student.rest.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.Grade;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.repository.BatchRepository;
import com.enuminfo.student.hibernate.repository.CourseRepository;
import com.enuminfo.student.hibernate.repository.GradeRepository;
import com.enuminfo.student.hibernate.repository.StudentRepsitory;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/student")
public class StudentService {

	@Autowired StudentRepsitory repository;
	@Autowired BatchRepository batchRepository;
	@Autowired CourseRepository courseRepository;
	@Autowired GradeRepository gradeRepository;
	
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
	
	@RequestMapping(value = "/grades", method = RequestMethod.POST)
	public void saveStudentGrades(@RequestBody List<Student> students) {
		for (Iterator<Student> studentIterator = students.iterator(); studentIterator.hasNext();) {
			Student student = studentIterator.next();
			System.out.println(student.getStudentId() + " - " + student.getStudentName());
			for (Iterator<Grade> gradeIterator = student.getGrades().iterator(); gradeIterator.hasNext();) {
				Grade grade = gradeIterator.next();
				grade.setStudent(student);
				gradeRepository.save(grade);
			}
		}
	}
}
