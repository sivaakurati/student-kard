/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.TimeTrackerEvent;

/**
 * @author Kumar
 */
public interface TimeTrackerEventRepository extends PagingAndSortingRepository<TimeTrackerEvent, Integer> {

}
