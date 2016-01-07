/**
 * 
 */
package com.enuminfo.school.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.school.model.User;

/**
 * @author Kumar
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	User findByUsername(String username);
}
