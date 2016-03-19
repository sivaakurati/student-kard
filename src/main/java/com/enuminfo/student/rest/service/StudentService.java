/**
 * 
 */
package com.enuminfo.student.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.AssignmentResult;
import com.enuminfo.student.hibernate.model.Grade;
import com.enuminfo.student.hibernate.model.Parent;
import com.enuminfo.student.hibernate.model.Role;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.model.User;
import com.enuminfo.student.hibernate.repository.AssignmentResultRespository;
import com.enuminfo.student.hibernate.repository.BatchRepository;
import com.enuminfo.student.hibernate.repository.CourseRepository;
import com.enuminfo.student.hibernate.repository.GradeRepository;
import com.enuminfo.student.hibernate.repository.LocationRepository;
import com.enuminfo.student.hibernate.repository.ParentRepository;
import com.enuminfo.student.hibernate.repository.RoleRepository;
import com.enuminfo.student.hibernate.repository.StudentRepsitory;
import com.enuminfo.student.hibernate.repository.UserRepository;
import com.enuminfo.student.util.DateTimeUtil;
import com.enuminfo.student.util.MailUtil;
import com.enuminfo.student.util.StringUtil;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/student")
public class StudentService {

	@Autowired ParentRepository parentRepository;
	@Autowired StudentRepsitory repository;
	@Autowired BatchRepository batchRepository;
	@Autowired CourseRepository courseRepository;
	@Autowired GradeRepository gradeRepository;
	@Autowired UserRepository userRepository;
	@Autowired RoleRepository roleRepository;
	@Autowired LocationRepository locationRepository;
	@Autowired AssignmentResultRespository assignmentResultRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Student> getAllStudents() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveStudent(@RequestBody Student student) {
		if (student.getStudentId() == null) {
			//if (student.getGender() != null && student.getGender().equals("male")) student.setPhoto("avatar5.png");
			//else if (student.getGender() != null && student.getGender().equals("female")) student.setPhoto("avatar2.png");
			student.setDateOfBirth(DateTimeUtil.convertGMT2ISTDate(student.getDob()));
			student.setDateOfJoining(DateTimeUtil.convertGMT2ISTDate(student.getDoj()));
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleRepository.findByRoleName("ROLE_STUDENT"));
			User user = new User();
			user.setUsername(student.getEmailAddress());
			user.setPassword(StringUtil.generatePassword());
			user.setRoles(roles);
			userRepository.save(user);
			student.setLocation(locationRepository.findOne(student.getLocation().getLocationId()));
			repository.save(student);
			new MailUtil().sendStudentDetail(student, user);
		} else {
			student.setDateOfBirth(DateTimeUtil.convertSqlDate2UtilDate(student.getDob()));
			student.setDateOfJoining(DateTimeUtil.convertSqlDate2UtilDate(student.getDoj()));
			student.setLocation(locationRepository.findOne(student.getLocation().getLocationId()));
			repository.save(student);
		}		
	}
	
	@RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable Integer studentId) {
		Student student = repository.findOne(studentId);
		repository.delete(student);
	}
	
	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(@PathVariable Integer studentId) {
		Student student = new Student();
		if (studentId != 0) student = repository.findOne(studentId);
		student.setDob(DateTimeUtil.convertSqlDate2String(student.getDateOfBirth().toString()));
		student.setDoj(DateTimeUtil.convertSqlDate2String(student.getDateOfJoining().toString()));
		return student;
	}
	
	@RequestMapping(value = "/batch/{batchId}/course/{courseId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Student> getAllStudentsByBatchNCourse(@PathVariable Integer batchId, @PathVariable Integer courseId) {
		return repository.findByBatchAndCourse(batchRepository.findOne(batchId), courseRepository.findOne(courseId));
	}
	
	@RequestMapping(value = "/grades", method = RequestMethod.POST)
	public void saveStudentGrades(@RequestBody List<Student> students) {
		for (Iterator<Student> studentIterator = students.iterator(); studentIterator.hasNext();) {
			Student student = studentIterator.next();
			for (Iterator<Grade> gradeIterator = student.getGrades().iterator(); gradeIterator.hasNext();) {
				Grade grade = gradeIterator.next();
				grade.setStudent(student);
				gradeRepository.save(grade);
			}
		}
	}
	
	@RequestMapping(value = "/assignment", method = RequestMethod.POST)
	public void saveStudentAssignment(@RequestBody Student student) {
		for (Iterator<AssignmentResult> iterator = student.getAssignmentResults().iterator(); iterator.hasNext();) {
			AssignmentResult result = iterator.next();
			result.setStudent(student);
			result.setSubmittedDate(new Date());
			assignmentResultRepository.save(result);
		}
	}
	
	@RequestMapping(value = "/parent/{parentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Student> getStudentByParent(@PathVariable Integer parentId) {
		Parent parent = parentRepository.findOne(parentId);
		return repository.findByParent(parent);
	}
}
