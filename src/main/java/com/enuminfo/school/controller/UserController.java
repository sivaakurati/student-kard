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
public class UserController {

	@RequestMapping(value = "/profile", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getProfilePage() {
		return "profile";
	}
}
