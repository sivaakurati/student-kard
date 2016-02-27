/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.User;

/**
 * @author Kumar
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	User findByUsername(String username);
}
