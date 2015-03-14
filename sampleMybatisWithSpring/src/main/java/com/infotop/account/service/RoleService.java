package com.infotop.account.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.infotop.account.mapper.RoleMapper;
import com.infotop.account.model.Role;


@Service
public class RoleService {
	
	@Resource
	private RoleMapper roleMapper;
	
	public Role findRoleByName(String roleName){
		return roleMapper.getRole(roleName);
	}
	
	public void add(Role role) {
		roleMapper.insertRole(role);  
	}

	
	public void edit(Role role) {
		roleMapper.updateRole(role);  
	}

	
}
