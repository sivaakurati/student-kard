/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Location;

/**
 * @author Kumar
 */
public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {

	Iterable<Location> findByCountry(Integer countryId);
	Iterable<Location> findByStateName(String stateName);
	Iterable<Location> findByCityName(String cityName);
}
