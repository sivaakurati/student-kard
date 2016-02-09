/**
 * 
 */
package com.enuminfo.school.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.school.hibernate.model.Role;
import com.enuminfo.school.hibernate.model.Teacher;
import com.enuminfo.school.hibernate.model.User;
import com.enuminfo.school.hibernate.repository.LocationRepository;
import com.enuminfo.school.hibernate.repository.RoleRepository;
import com.enuminfo.school.hibernate.repository.SubjectRepository;
import com.enuminfo.school.hibernate.repository.TeacherRepository;
import com.enuminfo.school.hibernate.repository.UserRepository;
import com.enuminfo.school.util.StringUtil;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherService {

	@Autowired TeacherRepository repository;
	@Autowired RoleRepository roleRepository;
	@Autowired UserRepository userRepository;
	@Autowired LocationRepository locationRepository;
	@Autowired SubjectRepository subjectRepository;
	
	@Autowired JavaMailSender mailSender;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Teacher> getAllTeachers() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveTeacher(@RequestBody Teacher teacher) {
		if (teacher.getTeacherId() == null) {
			if (teacher.getGender().equals("male")) teacher.setPhoto("avatar5.png");
			else teacher.setPhoto("avatar2.png");			
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleRepository.findByRoleName("ROLE_TEACHER"));
			if (teacher.getAdminRight() != null && teacher.getAdminRight() == true) roles.add(roleRepository.findByRoleName("ROLE_ADMIN"));
			User user = new User();
			user.setUsername(teacher.getEmailAddress());
			user.setPassword(StringUtil.generatePassword());
			user.setPassword("p@5530rd");
			user.setRoles(roles);
			userRepository.save(user);
		}
		teacher.setLocation(locationRepository.findOne(teacher.getLocation().getLocationId()));
		teacher.setSubject(subjectRepository.findOne(teacher.getSubject().getSubjectId()));
		repository.save(teacher);
	}
	
	@RequestMapping(value = "/{teacherId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteTeacher(@PathVariable Integer teacherId) {
		Teacher teacher = repository.findOne(teacherId);
		repository.delete(teacher);
	}
	
	@RequestMapping(value = "/{teacherId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Teacher getTeacher(@PathVariable Integer teacherId) {
		Teacher teacher = new Teacher();
		if (teacherId != 0) teacher = repository.findOne(teacherId);
		return teacher;
	}
}
