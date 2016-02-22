/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.Parent;

/**
 * @author Kumar
 */
public interface ParentRepository extends PagingAndSortingRepository<Parent, Integer> {

	@Query ("SELECT model FROM Parent model WHERE model.mainParentId = ?1")
	Iterable<Parent> findByMainPaent(Integer parentId);
}
