package com.enuminfo.student.rest.service;

import java.io.File;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.enuminfo.student.hibernate.model.Parent;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.model.Teacher;
import com.enuminfo.student.hibernate.repository.ParentRepository;
import com.enuminfo.student.hibernate.repository.StudentRepsitory;
import com.enuminfo.student.hibernate.repository.TeacherRepository;
import com.enuminfo.student.util.FileUtil;

/**
 * @author Chetan Hegde
 */
@RestController
@RequestMapping(value = "/file")
public class UploadService {
	
	@Autowired TeacherRepository teacherRepository;
	@Autowired ParentRepository parentRepository;
	@Autowired StudentRepsitory studentRepository;
	
	@Value("${photoPath}")
	private String photoPath;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addAttachmentFile(MultipartHttpServletRequest request) {
		String result = null;
		try {
			Iterator<String> itr =  request.getFileNames();
			MultipartFile multiFile = request.getFile(itr.next());
			File photoFile = new File(request.getServletContext().getRealPath("/") + File.separator + photoPath);
			if (!photoFile.exists()) photoFile.mkdir();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			Teacher teacherObj = null;
			teacherObj = teacherRepository.findByEmailAddress(username);
			if (teacherObj == null) {
				Parent parentObj = parentRepository.findByEmailAddress(username);    			
				if (parentObj == null) {
					Student studentObj = studentRepository.findByEmailAddress(username);
					studentObj.setPhoto(FileUtil.convertFileToByteArray(multiFile, photoFile.getAbsolutePath(), "student", studentObj.getStudentId()));
					studentObj.setImgName(multiFile.getOriginalFilename());
					studentRepository.save(studentObj);
				} else {
					parentObj.setPhoto(FileUtil.convertFileToByteArray(multiFile, photoFile.getAbsolutePath(), "parent", parentObj.getParentId()));
					parentObj.setImgName(multiFile.getOriginalFilename());
					parentRepository.save(parentObj);
				}
			} else {
				teacherObj.setPhoto(FileUtil.convertFileToByteArray(multiFile, photoFile.getAbsolutePath(), "teacher", teacherObj.getTeacherId()));
				teacherObj.setImgName(multiFile.getOriginalFilename());
				teacherRepository.save(teacherObj);
			}
			result = "success";
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}
}
