/**
 * 
 */
package com.enuminfo.school.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.school.hibernate.model.Teacher;
import com.enuminfo.school.hibernate.repository.TeacherRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping (value = "/user")
public class UserService {

	@Autowired TeacherRepository teacherRepository;
	
	@RequestMapping (method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getLoggerUserDetail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Teacher teacher = teacherRepository.findByEmailAddress(username);
		return teacher;
	}
}
