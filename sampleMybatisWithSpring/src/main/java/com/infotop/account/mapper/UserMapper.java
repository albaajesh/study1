package com.infotop.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;






import com.infotop.account.model.User;
import com.infotop.common.PageHelper;





public interface UserMapper {
	Long getId();
	String getPassword(String userName);
	public String getUsernameById(@Param("id") int id);
	void saveUser(User user);
	User getUserById(@Param("id") Long id);
	public Long getDatagridTotal(User user);
	User getUserUpdateById(Long id);
	void deleteUser(Long id);
	public List<User> datagridUser(PageHelper page);
	void deleteUserRoleByUserId(Long id);
	void insertUserRole(@Param("userId") Long id,@Param("roleId") Long roleId);
	User getUser(String userName);
	User login(Map<String,Object> param);
	User getUser(Map<String,Object> param);
	List<Map<String,Object>> getUserNameList();
	void insertUser(User user);
	//void insertUserRole(Map<String,Object> param);
	void updateUser(User user);
	void updateUserRole(Map<String,Object> param);
	
	void deleteUserRole(Map<String,Object> param);
	void changeUserPassword(Map<String,Object> param);
	
	
	
	
}
