/**
 * 
 */
package com.enuminfo.school.domain.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "profile";
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
	
	@RequestMapping(value = "/department", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getDepartmentPage() {
		return "department";
	}
	
	@RequestMapping(value = "/timetracker", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getTimeTrackerPage() {
		return "timetracker";
	}
	
	@RequestMapping(value = "/teacher", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getTeacherPage() {
		return "teacher";
	}
	
	@RequestMapping(value = "/teacher{teacherId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getSaveOrUpdateTeacherPage() {
		return "saveteacher";
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getStudentPage() {
		return "student";
	}
	
	@RequestMapping(value = "/student{studentId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getSaveOrUpdateStudentPage(@PathVariable Integer studentId) {
		return "savestudent";
	}
	
	@RequestMapping(value = "/parent", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getParentPage() {
		return "parent";
	}
	
	@RequestMapping(value = "/parent{parentId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getSaveOrUpdateParentPage(@PathVariable Integer parentId) {
		return "saveparent";
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getChangePasswordPage() {
		return "changepassword";
	}
}
