/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.AssignmentResult;
import com.enuminfo.student.hibernate.model.Student;

/**
 * @author Kumar
 */
public interface AssignmentResultRespository extends PagingAndSortingRepository<AssignmentResult, Integer> {

	Iterable<AssignmentResult> findByStudent(Student student);
}
