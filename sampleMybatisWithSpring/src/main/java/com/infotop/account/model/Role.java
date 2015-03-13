package com.infotop.account.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {

	private static final long serialVersionUID = -6179411339504526803L;
	
	private Long id;
	private String name;//角色名称
	private String description;
	/**
	 * 关联用户
	 */
	private Set<User> users = new HashSet<User>();
	
	/**
	 * 关联权限
	 */
	private Set<Permission> permissions = new HashSet<Permission>();
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
	
}