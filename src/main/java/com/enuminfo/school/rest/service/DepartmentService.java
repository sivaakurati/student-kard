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

import com.enuminfo.school.hibernate.model.Department;
import com.enuminfo.school.hibernate.repository.DepartmentRepository;

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
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveDepartment(@RequestBody Department department) {
		repository.save(department);
	}
	
	@RequestMapping(value = "/{departmentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteDepartment(@PathVariable Integer departmentId) {
		Department department = repository.findOne(departmentId);
		repository.delete(department);
	}
}
