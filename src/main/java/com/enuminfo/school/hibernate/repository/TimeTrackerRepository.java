/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.TimeTracker;

/**
 * @author Kumar
 */
public interface TimeTrackerRepository extends PagingAndSortingRepository<TimeTracker, Integer> {

}
