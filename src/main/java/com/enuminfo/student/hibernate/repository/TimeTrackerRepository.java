/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.TimeTracker;

/**
 * @author Kumar
 */
public interface TimeTrackerRepository extends PagingAndSortingRepository<TimeTracker, Integer> {

}
