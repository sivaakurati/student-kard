/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.CourseSubject;

/**
 * @author Kumar
 */
public interface CourseSubjectRepository extends PagingAndSortingRepository<CourseSubject, Integer> {

	@Query ("SELECT model.subject.subjectId FROM CourseSubject model WHERE model.course.courseId = ?1")
	Iterable<Integer> findByCourse(Integer courseId);
	
	@Query ("FROM CourseSubject model WHERE model.course.courseId = ?1 AND model.subject.subjectId = ?2")
	CourseSubject findByCourseAndSubject(Integer courseId, Integer subjectId);
}
