package com.infotop.account.mapper;

import java.util.List;
import java.util.Map;

import com.infotop.account.model.User;





public interface UserMapper {
	Long getId();
	String getPassword(String userName);
	User getUser(String userName);
	User login(Map<String,Object> param);
	User getUser(Map<String,Object> param);
	List<Map<String,Object>> getUserNameList();
	void insertUser(User user);
	void insertUserRole(Map<String,Object> param);
	void updateUser(User user);
	void updateUserRole(Map<String,Object> param);
	void deleteUser(Map<String,Object> param);
	void deleteUserRole(Map<String,Object> param);
	void changeUserPassword(Map<String,Object> param);
}
