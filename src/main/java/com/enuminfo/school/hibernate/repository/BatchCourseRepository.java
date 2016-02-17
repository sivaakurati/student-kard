/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.BatchCourse;

/**
 * @author Kumar
 */
public interface BatchCourseRepository extends PagingAndSortingRepository<BatchCourse, Integer> {
	
}
