/**
 * 
 */
package com.enuminfo.school.service;

import com.enuminfo.school.model.User;

/**
 * @author Kumar
 */
public interface UserService {
	
	public User loadByUserId(Integer userId);
	public User loadByUsername(String username);
}
