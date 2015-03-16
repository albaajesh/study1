package com.infotop.account.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

public class Role implements Serializable {

	private static final long serialVersionUID = -6179411339504526803L;
	
	private Long id;
	private String name;
	private String roleType;
	private int pid;
	private String remark;
	/*private List<String> permissionList = Lists.newArrayList();*/
	private List<String> permissionNameList = Lists.newArrayList();
	private String permissionIds;
	/**
	 * 关联用户
	 */
	/*private Set<User> users = new HashSet<User>();
	
	*//**
	 * 关联权限
	 *//*
	private Set<Permission> permissions = new HashSet<Permission>();*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*public List<String> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}*/
	public List<String> getPermissionNameList() {
		return permissionNameList;
	}
	public void setPermissionNameList(List<String> permissionNameList) {
		this.permissionNameList = permissionNameList;
	}
	public String getPermissionIds() {
		return permissionIds;
	}
	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}