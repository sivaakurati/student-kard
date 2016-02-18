/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.TimeTrackerEvent;

/**
 * @author Kumar
 */
public interface TimeTrackerEventRepository extends PagingAndSortingRepository<TimeTrackerEvent, Integer> {

}
