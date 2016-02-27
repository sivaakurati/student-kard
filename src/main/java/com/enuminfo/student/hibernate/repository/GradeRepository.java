/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Grade;

/**
 * @author Kumar
 */
public interface GradeRepository extends PagingAndSortingRepository<Grade, Integer> {

}
