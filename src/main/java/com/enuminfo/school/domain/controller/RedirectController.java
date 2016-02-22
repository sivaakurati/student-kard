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
	
	@RequestMapping(value = "/studentnparent", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getStudentNParentPage() {
		return "studentnparent";
	}
	
	@RequestMapping(value = "/studentnparent{parentId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getSaveOrUpdateStudentNParentPage() {
		return "savestudentnparent";
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getChangePasswordPage() {
		return "changepassword";
	}
}
