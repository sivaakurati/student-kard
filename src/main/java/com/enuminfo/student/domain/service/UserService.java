/**
 * 
 */
package com.enuminfo.student.domain.service;

import com.enuminfo.student.hibernate.model.User;

/**
 * @author Kumar
 */
public interface UserService {
	
	public User loadByUserId(Integer userId);
	public User loadByUsername(String username);
}
