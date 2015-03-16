package com.infotop.common;

import net.infotop.web.easyui.Message;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.mapper.JsonMapper;

import com.infotop.account.service.AuthenticationRealm.ShiroUser;
import com.infotop.common.log.BasicLogger;



public abstract class BasicController {
	@Autowired
	public BasicLogger logger;
	protected JsonMapper json = new JsonMapper();

	protected Message msg = new Message();

	public static final int PAGE_SIZE = 15;

	public static final String ROWS = "15";
	
	public static ShiroUser getLoginUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

}
