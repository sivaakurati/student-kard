/**
 * 
 */
package com.enuminfo.school.service;

import com.enuminfo.school.model.Batch;
import com.enuminfo.school.model.Country;
import com.enuminfo.school.model.Course;
import com.enuminfo.school.model.Location;

/**
 * @author Kumar
 */
public interface DataService {

	public void addCountry(Country country);
	public Iterable<Country> loadCountries();
	public Country loadCountryById(Integer countryId);
	public void editCountry(Country country);
	
	public void addLocation(Location location);
	public Iterable<Location> loadLocations();
	public Iterable<String> loadStatesByCountryId(Integer countryId);
	public Iterable<String> loadCitiesByStateName(String stateName);
	public Iterable<Location> loadLocationsByCityName(String cityName);
	public Location loadLocationByLocationId(Integer locationId);
	public void editLocation(Location location);
	
	public void addBatch(Batch batch);
	public Iterable<Batch> loadBatches();
	public Batch loadBatchById(Integer batchId);
	public void editBatch(Batch batch);
	
	public void addCourse(Course course);
	public Iterable<Course> loadCourses();
	public Course loadCourseById(Integer courseId);
	public void editCourse(Course course);
}
