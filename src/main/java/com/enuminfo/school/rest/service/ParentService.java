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

import com.enuminfo.school.hibernate.model.Parent;
import com.enuminfo.school.hibernate.repository.ParentRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/parent")
public class ParentService {

	@Autowired ParentRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Parent> getAllParents() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveParent(@RequestBody Parent parent) {
		repository.save(parent);
	}
	
	@RequestMapping(value = "/{parentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteParent(@PathVariable Integer parentId) {
		Parent parent = repository.findOne(parentId);
		repository.delete(parent);
	}
	
	@RequestMapping(value = "/{parentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Parent getParent(@PathVariable Integer parentId) {
		Parent parent = new Parent();
		if (parentId != 0) parent = repository.findOne(parentId);
		return parent;
	}
}
