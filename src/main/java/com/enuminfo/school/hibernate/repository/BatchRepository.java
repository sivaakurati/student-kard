/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Batch;

/**
 * @author Kumar
 */
public interface BatchRepository extends PagingAndSortingRepository<Batch, Integer> {

}
