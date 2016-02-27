/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.CourseSubject;

/**
 * @author Kumar
 */
public interface CourseSubjectRepository extends PagingAndSortingRepository<CourseSubject, Integer> {
	
}
