package com.infotop.account.mapper;

import java.util.List;
import java.util.Map;

import com.infotop.account.model.Role;






public interface RoleMapper {
	Long getId();
	Role getRole(String rolename);
	List<Role> getRoleList();
	List<Role> getRoleMenuList();
	void insertRole(Role role);
	void insertRoleMenu(Map<String,Object> param);
	void updateRole(Role role);
	void updateRoleMenu(Map<String,Object> param);
	void deleteRole(Map<String,Object> param);
	void deleteRoleMenu(Map<String,Object> param);
}
