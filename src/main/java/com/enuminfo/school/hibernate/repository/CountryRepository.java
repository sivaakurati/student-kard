/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Country;

/**
 * @author Kumar
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

}
