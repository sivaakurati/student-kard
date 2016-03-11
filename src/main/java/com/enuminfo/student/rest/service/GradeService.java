/**
 * 
 */
package com.enuminfo.student.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.Grade;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.repository.GradeRepository;
import com.enuminfo.student.hibernate.repository.StudentRepsitory;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/grade")
public class GradeService {

	@Autowired StudentRepsitory studentRepository;
	@Autowired GradeRepository repository;
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Grade> getAllGradesByStudent(@PathVariable Integer id) {
		Student student = studentRepository.findOne(id);
		return repository.findByStudent(student);
	}
}
