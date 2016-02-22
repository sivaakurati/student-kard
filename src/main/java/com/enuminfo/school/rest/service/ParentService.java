/**
 * 
 */
package com.enuminfo.school.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.school.hibernate.model.Parent;
import com.enuminfo.school.hibernate.model.Role;
import com.enuminfo.school.hibernate.model.Student;
import com.enuminfo.school.hibernate.model.User;
import com.enuminfo.school.hibernate.repository.BatchRepository;
import com.enuminfo.school.hibernate.repository.CourseRepository;
import com.enuminfo.school.hibernate.repository.LocationRepository;
import com.enuminfo.school.hibernate.repository.ParentRepository;
import com.enuminfo.school.hibernate.repository.RoleRepository;
import com.enuminfo.school.hibernate.repository.StudentRepsitory;
import com.enuminfo.school.hibernate.repository.UserRepository;
import com.enuminfo.school.util.StringUtil;

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
			//userRepository.save(user);
			//parent.setLocation(locationRepository.findOne(parent.getLocation().getLocationId()));
			parent.setMainParentId(null);
			//Parent savedMainParent = repository.save(parent);
			for (Parent dependent: parent.getDependents()) {
				if (dependent.getGender() != null && dependent.getGender().equals("male")) dependent.setPhoto("avatar5.png");
				else if (dependent.getGender() != null && dependent.getGender().equals("female")) dependent.setPhoto("avatar2.png");
				roles = new ArrayList<Role>();
				roles.add(roleRepository.findByRoleName("ROLE_PARENT"));
				user = new User();
				user.setUsername(parent.getEmailAddress());
				user.setPassword(StringUtil.generatePassword());
				user.setPassword("p@5530rd");
				user.setRoles(roles);
				//userRepository.save(user);
				dependent.setLocation(locationRepository.findOne(dependent.getLocation().getLocationId()));
				dependent.setMainParentId(parent);
				//repository.save(dependent);
			}
			for (Student child: parent.getChilds()) {
				if (child.getGender() != null && child.getGender().equals("male")) child.setPhoto("avatar5.png");
				else if (child.getGender() != null && child.getGender().equals("female")) child.setPhoto("avatar2.png");
				roles = new ArrayList<Role>();
				roles.add(roleRepository.findByRoleName("ROLE_STUDENT"));
				user = new User();
				user.setUsername(parent.getEmailAddress());
				user.setPassword(StringUtil.generatePassword());
				user.setPassword("p@5530rd");
				user.setRoles(roles);
				//userRepository.save(user);
				child.setParent(parent);
				child.setBatch(batchRepository.findOne(child.getBatch().getBatchId()));
				child.setCourse(courseRepository.findOne(child.getCourse().getCourseId()));
				//studentRepository.save(child);
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
		Parent parent = new Parent();
		if (parentId != 0) parent = repository.findOne(parentId);
		return parent;
	}
}
