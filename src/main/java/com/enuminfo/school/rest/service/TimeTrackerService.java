/**
 * 
 */
package com.enuminfo.school.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.school.hibernate.model.TimeTracker;
import com.enuminfo.school.hibernate.repository.TimeTrackerRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping (value = "/timetracker")
public class TimeTrackerService {

	
	@Autowired TimeTrackerRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<TimeTracker> getAllTimeTrackers() {
		return repository.findAll();
	}
}
