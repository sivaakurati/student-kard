/**
 * 
 */
package com.enuminfo.school.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.model.Subject;

/**
 * @author Kumar
 */
public interface SubjectRepository extends PagingAndSortingRepository<Subject, Integer> {

}
