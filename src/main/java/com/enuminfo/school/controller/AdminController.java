/**
 * 
 */
package com.enuminfo.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enuminfo.school.model.Course;
import com.enuminfo.school.service.DataService;

/**
 * @author Kumar
 */
@Controller
public class AdminController {
	
	@Autowired DataService dataService;
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Course> getAllCourses() {
		return dataService.loadCourses();
	}
}
