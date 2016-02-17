/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.CourseSubject;

/**
 * @author Kumar
 */
public interface CourseSubjectRepository extends PagingAndSortingRepository<CourseSubject, Integer> {
	
}
