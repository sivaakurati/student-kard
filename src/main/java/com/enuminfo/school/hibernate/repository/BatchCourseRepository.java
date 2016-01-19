/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.BatchCourse;

/**
 * @author Kumar
 */
public interface BatchCourseRepository extends PagingAndSortingRepository<BatchCourse, Integer> {

	@Query ("SELECT model.course.courseId FROM BatchCourse model WHERE model.batch.batchId = ?1")
	Iterable<Integer> findByBatch(Integer batchId);
	
	@Query ("FROM BatchCourse model WHERE model.batch.batchId = ?1 AND model.course.courseId = ?2")
	BatchCourse findByBatchAndCourse(Integer batchId, Integer courseId);
}
