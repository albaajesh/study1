package com.infotop.account.controller;


import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value = "/home")
public class MainController{

	
	/**
	 * 首页系统选择页面
	 * @return
	 */
	@RequestMapping(value = "")
	public String mainframe() {
		return "account/mainFrame";
	}
	
	
	
	
	
}
