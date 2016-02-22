/**
 * 
 */
package com.enuminfo.school.rest.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.school.hibernate.model.Department;
import com.enuminfo.school.hibernate.model.Role;
import com.enuminfo.school.hibernate.model.Subject;
import com.enuminfo.school.hibernate.model.Teacher;
import com.enuminfo.school.hibernate.model.User;
import com.enuminfo.school.hibernate.repository.CourseRepository;
import com.enuminfo.school.hibernate.repository.LocationRepository;
import com.enuminfo.school.hibernate.repository.RoleRepository;
import com.enuminfo.school.hibernate.repository.SubjectRepository;
import com.enuminfo.school.hibernate.repository.TeacherCourseRepository;
import com.enuminfo.school.hibernate.repository.TeacherRepository;
import com.enuminfo.school.hibernate.repository.TeacherSubjectRepository;
import com.enuminfo.school.hibernate.repository.UserRepository;
import com.enuminfo.school.util.DateTimeUtil;
import com.enuminfo.school.util.StringUtil;
import com.google.common.collect.Lists;

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
	@Autowired TeacherSubjectRepository teacherSubjectRepository;
	@Autowired TeacherCourseRepository teacherCourseRepository;
	@Autowired CourseRepository courseRepository;
	
	@Autowired JavaMailSender mailSender;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Teacher> getAllTeachers() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveTeacher(@RequestBody Teacher teacher) {
		if (teacher.getTeacherId() == null) {
			teacher.setDateOfBirth(DateTimeUtil.convertGMT2ISTDate(teacher.getDob()));
			teacher.setDateOfJoining(DateTimeUtil.convertGMT2ISTDate(teacher.getDoj()));
			if (teacher.getGender() != null && teacher.getGender().equals("male")) teacher.setPhoto("avatar5.png");
			else if (teacher.getGender() != null && teacher.getGender().equals("female")) teacher.setPhoto("avatar2.png");			
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleRepository.findByRoleName("ROLE_TEACHER"));
			for (Iterator<Department> iterator = teacher.getDepartments().iterator(); iterator.hasNext();) {
				Department department = (Department) iterator.next();
				Role role = new Role();
				role.setRoleName("ROLE_" + department.getDepartmentName().toUpperCase());
				roles.add(roleRepository.save(role));
			}
			User user = new User();
			user.setUsername(teacher.getEmailAddress());
			user.setPassword(StringUtil.generatePassword());
			user.setPassword("p@5530rd");
			user.setRoles(roles);
			userRepository.save(user);
			teacher.setLocation(locationRepository.findOne(teacher.getLocation().getLocationId()));
		}
		repository.save(teacher);
	}
	
	@RequestMapping(value = "/{teacherId}", method = RequestMethod.DELETE)
	public void deleteTeacher(@PathVariable Integer teacherId) {
		Teacher teacher = repository.findOne(teacherId);
		repository.delete(teacher);
	}
	
	@RequestMapping(value = "/{teacherId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Teacher getTeacher(@PathVariable Integer teacherId) {
		Teacher teacher = new Teacher();
		if (teacherId != 0) teacher = repository.findOne(teacherId);
		teacher.setSubjects(Lists.newArrayList(subjectRepository.findAll(teacherSubjectRepository.findByTeacherId(teacherId))));
		teacher.setCourses(Lists.newArrayList(courseRepository.findAll(teacherCourseRepository.findByTeacherId(teacherId))));
		return teacher;
	}
	
	@RequestMapping(value = "/{teacherId}/subjects", method = RequestMethod.GET)
	public List<Subject> getTeacherSubjectsById(@PathVariable Integer teacherId) {
		Set<Subject> subjects = new HashSet<Subject>();
		Teacher teacher = repository.findOne(teacherId);
		for (Subject subject: teacher.getSubjects()) {
			subjects.add(subject);
		}
		return new ArrayList<Subject>(subjects);
	}
}
