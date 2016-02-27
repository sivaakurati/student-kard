/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Department;

/**
 * @author Kumar
 */
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer> {

}
