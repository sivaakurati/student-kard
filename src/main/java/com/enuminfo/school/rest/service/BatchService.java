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

import com.enuminfo.school.hibernate.model.Batch;
import com.enuminfo.school.hibernate.repository.BatchRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/batch")
public class BatchService {

	@Autowired BatchRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Batch> getAllBatchs() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveBatch(@RequestBody Batch batch) {
		repository.save(batch);
	}
	
	@RequestMapping(value = "/{batchId}", method = RequestMethod.DELETE)
	public void deleteCourse(@PathVariable Integer batchId) {
		Batch batch = repository.findOne(batchId);
		repository.delete(batch);
	}
}
