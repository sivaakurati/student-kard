/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Grade;

/**
 * @author Kumar
 */
public interface GradeRepository extends PagingAndSortingRepository<Grade, Integer> {

}
