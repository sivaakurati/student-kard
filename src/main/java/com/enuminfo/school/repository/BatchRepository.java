/**
 * 
 */
package com.enuminfo.school.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.model.Batch;

/**
 * @author Kumar
 */
public interface BatchRepository extends PagingAndSortingRepository<Batch, Integer> {

}
