/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Department;

/**
 * @author Kumar
 */
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer> {

}
