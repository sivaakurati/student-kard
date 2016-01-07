/**
 * 
 */
package com.enuminfo.school.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.model.Course;

/**
 * @author Kumar
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer>{

}
