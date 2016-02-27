/**
 * 
 */
package com.enuminfo.student.rest.service;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.Location;
import com.enuminfo.student.hibernate.repository.LocationRepository;

/**
 * @author Kumar
 */
@RestController
public class LocationService {

	@Autowired LocationRepository repository;
	
	@RequestMapping(value = "/states", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<String> getAllStates() {
		Iterable<Location> locations = repository.findAll();
		Set<String> states = new TreeSet<String>();
		for (Iterator<Location> iterator = locations.iterator(); iterator.hasNext();) {
			Location location = (Location) iterator.next();
			states.add(location.getStateName().trim());
		}
		return states;
	}
	
	@RequestMapping(value = "/cities/{stateName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<String> getAllCities(@PathVariable String stateName) {
		Iterable<Location> locations = repository.findByStateName(stateName);
		Set<String> cities = new TreeSet<String>();
		for (Iterator<Location> iterator = locations.iterator(); iterator.hasNext();) {
			Location location = (Location) iterator.next();
			cities.add(location.getCityName().trim());
		}
		return cities;
	}
	
	@RequestMapping(value = "/locations/{cityName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Location> getAllLocations(@PathVariable String cityName) {
		return repository.findByCityName(cityName);
	}
	
	@RequestMapping(value = "/location/{locationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Location getLocation(@PathVariable Integer locationId) {
		return repository.findOne(locationId);
	}
}
