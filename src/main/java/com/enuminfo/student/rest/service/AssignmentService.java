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

import com.enuminfo.student.hibernate.model.Assignment;
import com.enuminfo.student.hibernate.repository.AssignmentRespository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/assignment")
public class AssignmentService {

	@Autowired AssignmentRespository repository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Assignment> getAllAssignments() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveAssignment(@RequestBody Assignment assignment) {
		repository.save(assignment);
	}
}
