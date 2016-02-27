/**
 * 
 */
package com.enuminfo.student.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.Teacher;
import com.enuminfo.student.hibernate.model.User;
import com.enuminfo.student.hibernate.repository.TeacherRepository;
import com.enuminfo.student.hibernate.repository.UserRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping (value = "/user")
public class UserService {

	@Autowired TeacherRepository teacherRepository;
	@Autowired UserRepository userRepository;
	
	@RequestMapping (method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getLoggerUserDetail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Teacher teacher = teacherRepository.findByEmailAddress(username);
		return teacher;
	}
	
	@RequestMapping (value = "/logged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getLoggerUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return userRepository.findByUsername(username);
	}
	
	@RequestMapping (value = "/logged/save", method = RequestMethod.POST)
	public void saveUserPassword(@RequestBody User user) {
		userRepository.save(user);
	}
}
