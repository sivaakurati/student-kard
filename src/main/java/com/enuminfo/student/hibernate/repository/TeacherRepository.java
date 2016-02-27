/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Teacher;

/**
 * @author Kumar
 */
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Integer> {

	Teacher findByEmailAddress(String emailAddress);
}
