/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Parent;

/**
 * @author Kumar
 */
public interface ParentRepository extends PagingAndSortingRepository<Parent, Integer> {

	@Query ("SELECT model FROM Parent model WHERE model.mainParentId = ?1")
	Iterable<Parent> findByMainPaent(Integer parentId);
	
	Parent findByEmailAddress(String emailAddress);
}
