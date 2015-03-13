package com.infotop.account.controller;

import org.apache.shiro.SecurityUtils;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
@RequestMapping(value = "/login")
public class LoginController {

	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		if (SecurityUtils.getSubject().getSession() != null) {
			SecurityUtils.getSubject().logout();
		}
		
		return "account/login";
	}


	

}
