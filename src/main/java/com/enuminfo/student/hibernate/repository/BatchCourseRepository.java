/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.BatchCourse;

/**
 * @author Kumar
 */
public interface BatchCourseRepository extends PagingAndSortingRepository<BatchCourse, Integer> {
	
}
