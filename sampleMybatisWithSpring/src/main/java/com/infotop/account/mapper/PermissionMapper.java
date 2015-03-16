/**
 * 
 */
package com.infotop.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.infotop.account.model.Permission;

/**
 * @Description: TODO
 * @author wgw
 * 创建时间：2012-3-2 下午02:14:24   
 */
public interface PermissionMapper {

	List<String> getPermissionByRoleId(Long id);

	Permission findPermissionByValue(String i);

	List<Permission> getAllPermission();

	String getPermissionValueById(String id);

	void saveRolePermission(@Param("id") Long id,@Param("value") String value);
	
	
	
	

}
