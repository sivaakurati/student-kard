/**
 * 
 */
package com.enuminfo.school.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.model.Country;

/**
 * @author Kumar
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

}
