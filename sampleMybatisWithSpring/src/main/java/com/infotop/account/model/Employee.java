package com.infotop.account.model;

import java.io.Serializable;

public class Employee implements Serializable{
	private static final long serialVersionUID = -5268898209820709788L;
	private Long id;
	private String name;
	private String age;
	private String department;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	

}
