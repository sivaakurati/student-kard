/**
 * 
 */
package com.enuminfo.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enuminfo.school.model.Batch;
import com.enuminfo.school.model.Country;
import com.enuminfo.school.model.Course;
import com.enuminfo.school.model.Location;
import com.enuminfo.school.repository.CourseRepository;
import com.enuminfo.school.service.DataService;

/**
 * @author Kumar
 */
@Service
public class DataServiceImpl implements DataService {

	@Autowired CourseRepository courseRepository;
	
	@Override
	public void addCountry(Country country) {
		
	}

	@Override
	public Iterable<Country> loadCountries() {
		return null;
	}

	@Override
	public Country loadCountryById(Integer countryId) {
		return null;
	}

	@Override
	public void editCountry(Country country) {
		
	}

	@Override
	public void addLocation(Location location) {
		
	}

	@Override
	public Iterable<Location> loadLocations() {
		return null;
	}

	@Override
	public Iterable<String> loadStatesByCountryId(Integer countryId) {
		return null;
	}

	@Override
	public Iterable<String> loadCitiesByStateName(String stateName) {
		return null;
	}

	@Override
	public Iterable<Location> loadLocationsByCityName(String cityName) {
		return null;
	}

	@Override
	public Location loadLocationByLocationId(Integer locationId) {
		return null;
	}

	@Override
	public void editLocation(Location location) {
		
	}

	@Override
	public void addBatch(Batch batch) {
		
	}

	@Override
	public Iterable<Batch> loadBatches() {
		return null;
	}

	@Override
	public Batch loadBatchById(Integer batchId) {
		return null;
	}

	@Override
	public void editBatch(Batch batch) {
		
	}

	@Override
	public void addCourse(Course course) {
		
	}

	@Override
	public Iterable<Course> loadCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course loadCourseById(Integer courseId) {
		return null;
	}

	@Override
	public void editCourse(Course course) {
		
	}
}
