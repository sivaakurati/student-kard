/**
 * 
 */
package com.enuminfo.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enuminfo.school.model.User;
import com.enuminfo.school.repository.UserRepository;
import com.enuminfo.school.service.UserService;

/**
 * @author Kumar
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired UserRepository userRepository;
	
	@Override
	public User loadByUserId(Integer userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public User loadByUsername(String username) {
		return userRepository.findByUsername(username);
	}	
}
