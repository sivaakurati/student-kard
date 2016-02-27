/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Batch;

/**
 * @author Kumar
 */
public interface BatchRepository extends PagingAndSortingRepository<Batch, Integer> {

}
