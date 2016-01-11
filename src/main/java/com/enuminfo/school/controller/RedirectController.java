/**
 * 
 */
package com.enuminfo.school.controller;

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
	
	@RequestMapping(value = "/batch", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getBatchPage() {
		return "batch";
	}
	
	@RequestMapping(value = "/course", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getCoursePage() {
		return "course";
	}
}
