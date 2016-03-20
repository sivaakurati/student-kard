/**
 * 
 */
package com.enuminfo.student.rest.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enuminfo.student.hibernate.model.Parent;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.model.Teacher;
import com.enuminfo.student.hibernate.model.User;
import com.enuminfo.student.hibernate.repository.ParentRepository;
import com.enuminfo.student.hibernate.repository.StudentRepsitory;
import com.enuminfo.student.hibernate.repository.TeacherRepository;
import com.enuminfo.student.hibernate.repository.UserRepository;
import com.enuminfo.student.util.FileUtil;

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
	
	@Value("${photoPath}")
	private String photoPath;
	
	@RequestMapping (method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getLoggerUserDetail(HttpServletRequest request) {
		File photoFile = new File(request.getServletContext().getRealPath("/") + File.separator + photoPath);
		if (!photoFile.exists()) photoFile.mkdir();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Object loggedObject = null;
		Teacher teacherObj = teacherRepository.findByEmailAddress(username);
		if (teacherObj == null) {
			Parent parentObject = parentRepository.findByEmailAddress(username);
			if (parentObject == null) {
				Student studentObj = studentRepository.findByEmailAddress(username);
				if (studentObj.getPhoto() == null) {
					if (studentObj.getGender() != null && studentObj.getGender().equals("male")) studentObj.setImgName("avatar5.png");
					else if (studentObj.getGender() != null && studentObj.getGender().equals("female")) studentObj.setImgName("avatar2.png");
				} else {
					FileUtil.convertByteArrayToFile(studentObj.getPhoto(), photoFile.getAbsolutePath(), "student", studentObj.getStudentId(), studentObj.getImgName());
					studentObj.setImgName("student" + File.separator + studentObj.getStudentId() + File.separator + studentObj.getImgName());
				}
				loggedObject = studentObj;
			} else {
				if (parentObject.getPhoto() == null) {
					if (parentObject.getGender() != null && parentObject.getGender().equals("male")) parentObject.setImgName("avatar5.png");
					else if (parentObject.getGender() != null && parentObject.getGender().equals("female")) parentObject.setImgName("avatar2.png");
				} else {
					FileUtil.convertByteArrayToFile(parentObject.getPhoto(), photoFile.getAbsolutePath(), "parent", parentObject.getParentId(), parentObject.getImgName());
					parentObject.setImgName("parent" + File.separator + parentObject.getParentId() + File.separator + parentObject.getImgName());
				}
				loggedObject = parentObject;
			}
		} else {
			if (teacherObj.getPhoto() == null) {
				if (teacherObj.getGender() != null && teacherObj.getGender().equals("male")) teacherObj.setImgName("avatar5.png");
				else if (teacherObj.getGender() != null && teacherObj.getGender().equals("female")) teacherObj.setImgName("avatar2.png");
			} else {
				FileUtil.convertByteArrayToFile(teacherObj.getPhoto(), photoFile.getAbsolutePath(), "teacher", teacherObj.getTeacherId(), teacherObj.getImgName());
				teacherObj.setImgName("teacher" + File.separator + teacherObj.getTeacherId() + File.separator + teacherObj.getImgName());
			}
			loggedObject = teacherObj;
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
