package com.infotop.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.infotop.account.model.Role;
import com.infotop.account.model.User;
import com.infotop.common.PageHelper;






public interface RoleMapper {
	
	public Long getDatagridTotalForRole(Role role);
	public List<Role> datagridRole(PageHelper page);
	public List<Role> getAllRole();
	public Role getRoleById(@Param("id") Long id);
	public void deleteRolePermissionById(@Param("id") Long id);
	
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
	//public Role getRoleById(Long id);
	
	
}
