/**
 * 
 */
package com.enuminfo.school.domin.service;

import com.enuminfo.school.hibernate.model.User;

/**
 * @author Kumar
 */
public interface UserService {
	
	public User loadByUserId(Integer userId);
	public User loadByUsername(String username);
}
