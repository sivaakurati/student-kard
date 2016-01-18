/**
 * 
 */
package com.enuminfo.school.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enuminfo.school.domin.service.UserService;
import com.enuminfo.school.hibernate.model.User;
import com.enuminfo.school.hibernate.repository.UserRepository;

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
