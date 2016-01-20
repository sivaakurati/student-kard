/**
 * 
 */
package com.enuminfo.school.rest.service;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.school.hibernate.model.Location;
import com.enuminfo.school.hibernate.repository.LocationRepository;

/**
 * @author Kumar
 */
@RestController
public class LocationService {

	@Autowired LocationRepository repository;
	
	@RequestMapping(value = "/state", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<String> getAllStates() {
		Iterable<Location> locations = repository.findAll();
		Set<String> states = new TreeSet<String>();
		for (Iterator<Location> iterator = locations.iterator(); iterator.hasNext();) {
			Location location = (Location) iterator.next();
			states.add(location.getStateName().trim());
		}
		return states;
	}
}
