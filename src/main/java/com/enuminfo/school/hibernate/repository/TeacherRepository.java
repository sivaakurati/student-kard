/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Teacher;

/**
 * @author Kumar
 */
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Integer> {

}
