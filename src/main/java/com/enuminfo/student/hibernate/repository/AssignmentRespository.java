/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Assignment;

/**
 * @author Kumar
 */
public interface AssignmentRespository extends PagingAndSortingRepository<Assignment, Integer> {

}
