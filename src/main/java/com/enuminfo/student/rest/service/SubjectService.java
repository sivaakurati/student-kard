/**
 * 
 */
package com.enuminfo.student.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.Subject;
import com.enuminfo.student.hibernate.repository.SubjectRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/subject")
public class SubjectService {

	@Autowired SubjectRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Subject> getAllSubjects() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveSubject(@RequestBody Subject subject) {
		repository.save(subject);
	}
	
	@RequestMapping(value = "/{subjectId}", method = RequestMethod.DELETE)
	public void deleteSubject(@PathVariable Integer subjectId) {
		Subject subject = repository.findOne(subjectId);
		repository.delete(subject);
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Subject getBySubjectName(@PathVariable String name) {
		return repository.findBySubjectName(name);
	}
}
