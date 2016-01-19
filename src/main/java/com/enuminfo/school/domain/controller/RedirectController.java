/**
 * 
 */
package com.enuminfo.school.domain.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Kumar
 */
@Controller
public class RedirectController {

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getLoginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getHomePage() {
		return "home";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getProfilePage() {
		return "profile";
	}
	
	@RequestMapping(value = "/batch", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getBatchPage() {
		return "batch";
	}
	
	@RequestMapping(value = "/course", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getCoursePage() {
		return "course";
	}
	
	@RequestMapping(value = "/subject", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getSubjectPage() {
		return "subject";
	}
	
	@RequestMapping(value = "/calendar", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getCalendarPage() {
		return "calendar";
	}
	
	@RequestMapping(value = "/teacher", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getTeacherPage() {
		return "teacher";
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getStudentPage() {
		return "student";
	}
	
	@RequestMapping(value = "/parent", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getParentPage() {
		return "parent";
	}
}
