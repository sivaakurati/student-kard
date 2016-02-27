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

import com.enuminfo.student.hibernate.model.Department;
import com.enuminfo.student.hibernate.repository.DepartmentRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/department")
public class DepartmentService {

	@Autowired DepartmentRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Department> getAllDepartments() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveDepartment(@RequestBody Department department) {
		repository.save(department);
	}
	
	@RequestMapping(value = "/{departmentId}", method = RequestMethod.DELETE)
	public void deleteDepartment(@PathVariable Integer departmentId) {
		Department department = repository.findOne(departmentId);
		repository.delete(department);
	}
}
