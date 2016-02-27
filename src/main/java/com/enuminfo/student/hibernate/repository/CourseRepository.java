/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Course;

/**
 * @author Kumar
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer>{

}
