/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Batch;
import com.enuminfo.school.hibernate.model.Course;
import com.enuminfo.school.hibernate.model.Parent;
import com.enuminfo.school.hibernate.model.Student;

/**
 * @author Kumar
 */
public interface StudentRepsitory extends PagingAndSortingRepository<Student, Integer> {

	Iterable<Student> findByParent(Parent parent);
	Iterable<Student> findByBatchAndCourse(Batch batch, Course course);
}
