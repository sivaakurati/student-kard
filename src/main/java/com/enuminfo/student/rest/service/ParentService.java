/**
 * 
 */
package com.enuminfo.student.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.Parent;
import com.enuminfo.student.hibernate.model.Role;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.model.User;
import com.enuminfo.student.hibernate.repository.BatchRepository;
import com.enuminfo.student.hibernate.repository.CourseRepository;
import com.enuminfo.student.hibernate.repository.LocationRepository;
import com.enuminfo.student.hibernate.repository.ParentRepository;
import com.enuminfo.student.hibernate.repository.RoleRepository;
import com.enuminfo.student.hibernate.repository.StudentRepsitory;
import com.enuminfo.student.hibernate.repository.UserRepository;
import com.enuminfo.student.util.DateTimeUtil;
import com.enuminfo.student.util.StringUtil;
import com.google.common.collect.Lists;

/**
 * @author Kumar
 */
@RestController
@RequestMapping(value = "/parent")
public class ParentService {

	@Autowired ParentRepository repository;
	@Autowired RoleRepository roleRepository;
	@Autowired UserRepository userRepository;
	@Autowired LocationRepository locationRepository;
	@Autowired BatchRepository batchRepository;
	@Autowired CourseRepository courseRepository;
	@Autowired StudentRepsitory studentRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Parent> getAllParents() {
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveParent(@RequestBody Parent parent) {
		if (parent.getParentId() == null) {
			if (parent.getGender() != null && parent.getGender().equals("male")) parent.setPhoto("avatar5.png");
			else if (parent.getGender() != null && parent.getGender().equals("female")) parent.setPhoto("avatar2.png");
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleRepository.findByRoleName("ROLE_PARENT"));
			User user = new User();
			user.setUsername(parent.getEmailAddress());
			user.setPassword(StringUtil.generatePassword());
			user.setPassword("p@5530rd");
			user.setRoles(roles);
			userRepository.save(user);
			parent.setLocation(locationRepository.findOne(parent.getLocation().getLocationId()));
			parent.setMainParentId(null);
			Parent savedMainParent = repository.save(parent);
			for (Parent dependent: parent.getDependents()) {
				if (dependent.getParentName() != null) {
					if (dependent.getGender() != null && dependent.getGender().equals("male")) dependent.setPhoto("avatar5.png");
					else if (dependent.getGender() != null && dependent.getGender().equals("female")) dependent.setPhoto("avatar2.png");
					roles = new ArrayList<Role>();
					roles.add(roleRepository.findByRoleName("ROLE_PARENT"));
					user = new User();
					user.setUsername(dependent.getEmailAddress());
					user.setPassword(StringUtil.generatePassword());
					user.setPassword("p@5530rd");
					user.setRoles(roles);
					userRepository.save(user);
					dependent.setAddress(savedMainParent.getAddress());
					dependent.setLocation(savedMainParent.getLocation());
					dependent.setMainParentId(savedMainParent.getParentId());
					repository.save(dependent);
				}
			}
			for (Student child: parent.getChilds()) {
				if (child.getStudentName() != null) {
					if (child.getGender() != null && child.getGender().equals("male")) child.setPhoto("avatar5.png");
					else if (child.getGender() != null && child.getGender().equals("female")) child.setPhoto("avatar2.png");
					roles = new ArrayList<Role>();
					roles.add(roleRepository.findByRoleName("ROLE_STUDENT"));
					user = new User();
					user.setUsername(child.getEmailAddress());
					user.setPassword(StringUtil.generatePassword());
					user.setPassword("p@5530rd");
					user.setRoles(roles);
					userRepository.save(user);
					child.setDateOfBirth(DateTimeUtil.convertGMT2ISTDate(child.getDob()));
					child.setDateOfJoining(DateTimeUtil.convertGMT2ISTDate(child.getDoj()));
					child.setParent(parent);
					studentRepository.save(child);
				}
			}
		} else {
			repository.save(parent);
			for (Parent dependent: parent.getDependents()) {
				repository.save(dependent);
			}
		}
	}
	
	@RequestMapping(value = "/{parentId}", method = RequestMethod.DELETE)
	public void deleteParent(@PathVariable Integer parentId) {
		Parent parent = repository.findOne(parentId);
		repository.delete(parent);
	}
	
	@RequestMapping(value = "/{parentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Parent getParent(@PathVariable Integer parentId) {
		Parent parent = null;
		if (parentId == 0) {
			parent = new Parent();
			List<Parent> dependents = new ArrayList<Parent>();
			dependents.add(new Parent());
			dependents.add(new Parent());
			dependents.add(new Parent());
			dependents.add(new Parent());
			parent.setDependents(dependents);
			List<Student> childs = new ArrayList<Student>();
			childs.add(new Student());
			childs.add(new Student());
			childs.add(new Student());
			childs.add(new Student());
			parent.setChilds(childs);
		} else {
			parent = repository.findOne(parentId);
			List<Parent> dependents = new ArrayList<Parent>();
			dependents.addAll(Lists.newArrayList(repository.findByMainPaent(parentId)));
			parent.setDependents(dependents);
		}
		return parent;
	}
}
