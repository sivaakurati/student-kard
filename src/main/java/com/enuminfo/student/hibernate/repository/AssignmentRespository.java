/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Assignment;
import com.enuminfo.student.hibernate.model.Batch;
import com.enuminfo.student.hibernate.model.Course;

/**
 * @author Kumar
 */
public interface AssignmentRespository extends PagingAndSortingRepository<Assignment, Integer> {

	Iterable<Assignment> findByBatchAndCourse(Batch batch, Course course);
}
