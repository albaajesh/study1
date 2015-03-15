package com.infotop.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import com.infotop.account.mapper.RoleMapper;
import com.infotop.account.mapper.UserMapper;
import com.infotop.account.model.Role;
import com.infotop.account.model.User;
import com.infotop.common.PageHelper;

@Component
public class AccountService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	public User findUserByName(String username) {
		return userMapper.getUser(username);
	}

	public Role findRoleByName(String roleName){
		return roleMapper.getRole(roleName);
	}
	
	public Long getDatagridTotal(User user) {
		return userMapper.getDatagridTotal(user);  
	}
	
	public List<User> datagridUser(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return userMapper.datagridUser(page);  
	}
}
