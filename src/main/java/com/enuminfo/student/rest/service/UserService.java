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

import com.enuminfo.student.hibernate.model.User;
import com.enuminfo.student.hibernate.repository.ParentRepository;
import com.enuminfo.student.hibernate.repository.StudentRepsitory;
import com.enuminfo.student.hibernate.repository.TeacherRepository;
import com.enuminfo.student.hibernate.repository.UserRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping (value = "/user")
public class UserService {

	@Autowired TeacherRepository teacherRepository;
	@Autowired ParentRepository parentRepository;
	@Autowired StudentRepsitory studentRepository;
	@Autowired UserRepository userRepository;
	
	@RequestMapping (method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getLoggerUserDetail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Object loggedObject = null;
		loggedObject = teacherRepository.findByEmailAddress(username);
		if (loggedObject == null) {
			loggedObject = parentRepository.findByEmailAddress(username);
			if (loggedObject == null) loggedObject = studentRepository.findByEmailAddress(username);
		}
		return loggedObject;
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
