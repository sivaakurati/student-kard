/**
 * 
 */
package com.enuminfo.student.hibernate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enuminfo.student.hibernate.model.Role;

/**
 * @author Kumar
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

	Role findByRoleName(String roleName);
}
