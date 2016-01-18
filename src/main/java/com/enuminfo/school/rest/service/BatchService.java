/**
 * 
 */
package com.enuminfo.school.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveBatch(@RequestBody Batch batch) {
		repository.save(batch);
	}
}
