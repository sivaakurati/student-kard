/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Grade;
import com.enuminfo.student.hibernate.model.Student;

/**
 * @author Kumar
 */
public interface GradeRepository extends PagingAndSortingRepository<Grade, Integer> {

	Iterable<Grade> findByStudent(Student student);
}
