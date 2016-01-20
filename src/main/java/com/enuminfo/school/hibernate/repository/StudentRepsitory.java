/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Student;

/**
 * @author Kumar
 */
public interface StudentRepsitory extends PagingAndSortingRepository<Student, Integer> {

}
