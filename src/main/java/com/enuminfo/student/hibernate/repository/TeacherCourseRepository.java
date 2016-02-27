/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.TeacherCourse;

/**
 * @author Kumar
 */
public interface TeacherCourseRepository extends PagingAndSortingRepository<TeacherCourse, Integer> {

	@Query ("SELECT model.course.courseId FROM TeacherCourse model WHERE model.teacher.teacherId = ?1")
	Iterable<Integer> findByTeacherId(Integer teacherId);
}
