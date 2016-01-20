/**
 * 
 */
package com.enuminfo.school.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.school.hibernate.model.Teacher;
import com.enuminfo.school.hibernate.repository.TeacherRepository;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherService {

	@Autowired TeacherRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Teacher> getAllTeachers() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveTeacher(@RequestBody Teacher teacher) {
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
