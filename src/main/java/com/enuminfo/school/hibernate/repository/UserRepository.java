/**
 * 
 */
package com.enuminfo.school.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.hibernate.model.User;

/**
 * @author Kumar
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	User findByUsername(String username);
}
