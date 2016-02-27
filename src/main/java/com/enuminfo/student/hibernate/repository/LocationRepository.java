/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Location;

/**
 * @author Kumar
 */
public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {

	Iterable<Location> findByStateName(String stateName);
	Iterable<Location> findByCityName(String cityName);
}
