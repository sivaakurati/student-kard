package com.enuminfo.student.rest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.enuminfo.student.hibernate.model.Parent;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.model.Teacher;
import com.enuminfo.student.hibernate.repository.ParentRepository;
import com.enuminfo.student.hibernate.repository.StudentRepsitory;
import com.enuminfo.student.hibernate.repository.TeacherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Chetan Hegde
 */
@RestController
@RequestMapping(value = "/upload")
public class UploadService {
	
	@Autowired TeacherRepository teacherRepository;
	@Autowired ParentRepository parentRepository;
	@Autowired StudentRepsitory studentRepository;
	
	@Value("${photoPath}")
	private String photoPath;
	
	@RequestMapping(value = "/addAttachmentFile", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addAttachmentFile(MultipartHttpServletRequest request, RedirectAttributes model) throws IOException {
    	try {  
    		System.out.println("inside addAttachmentFile");
			Iterator<String> itr =  request.getFileNames();    	 
            MultipartFile multiFile = request.getFile(itr.next());            
    		String fileName = multiFile.getOriginalFilename();
            System.out.println(fileName);
            String saveDirectory = photoPath;//"C://Users/chetan/Desktop/";
            System.out.println(saveDirectory);
            File file = new File(saveDirectory);
    		if (!file.exists()) {
    			if (file.mkdir()) {
    				//LOG.info("Directory is created!");
    			} else {
    				//LOG.info("Failed to create directory!");
    			}
    		}
    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		String username = authentication.getName();
    		System.out.println("user : "+username);
    		Teacher teacherObj = null;
    		teacherObj = teacherRepository.findByEmailAddress(username);
    		if (teacherObj == null) {
    			Parent parent = parentRepository.findByEmailAddress(username);    			
    			if (parent == null) {
    				System.out.println("stdnt");
    				Student student = studentRepository.findByEmailAddress(username);
    				//File imgfile = new File(multiFile);
    				try{ 
    					File fl = new File(fileName);
    					String renameFile = "Std_"+student.getStudentId()+student.getStudentName()+".jpg";
    		    		File imgfile = new File(saveDirectory + renameFile);
    		    		fl.renameTo(imgfile);
    		    		multiFile.transferTo(imgfile);
    		            System.out.println("copied!!!");
    		            
    				  FileInputStream fin = new FileInputStream(imgfile);
    				  byte[] fileBytes = new byte[(int) imgfile.length()];
    				  System.out.println("file bytes :: "+fileBytes);
    				  fin.read(fileBytes);
    				  fin.close();
    				  student.setImgName(renameFile);
    				//student.setPhoto(fileBytes);
    				studentRepository.save(student);
    				System.out.println("Successfully inserted the file into the database!");
    				} catch(Exception ex) {
    					//ex.getStackTrace();
    					System.out.println(ex.getMessage());
    				}
    			} else {
    				System.out.println("Parent");
    				//parent.setPhoto(saveDirectory + fileName);
    				parentRepository.save(parent);
    			}
    		} else {
    			System.out.println("teacher");
    			//teacherObj.setPhoto(saveDirectory + fileName);
    			teacherRepository.save(teacherObj);
    		}
            
            
            
			
    	} catch (Exception ex) {
    		ex.getMessage();
			//LOG.error(ex.getMessage());
			//model.addAttribute("errorMsg", ESeparationUtil.ERROR_MSG);
			//return "errorPage";
		}
		return toJson("File Uploaded successfully.");    	
	}
	
	public String toJson(Object data)
    {
        ObjectMapper mapper=new ObjectMapper();
        StringBuilder builder=new StringBuilder();
        try {
            builder.append(mapper.writeValueAsString(data));
        } catch (Exception e) {            
            e.printStackTrace();
        }  return builder.toString();
    }

}
