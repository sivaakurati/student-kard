/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Batch;
import com.enuminfo.student.hibernate.model.Course;
import com.enuminfo.student.hibernate.model.Parent;
import com.enuminfo.student.hibernate.model.Student;

/**
 * @author Kumar
 */
public interface StudentRepsitory extends PagingAndSortingRepository<Student, Integer> {

	Iterable<Student> findByParent(Parent parent);
	Iterable<Student> findByBatchAndCourse(Batch batch, Course course);
	Student findByEmailAddress(String emailAddress);
}
