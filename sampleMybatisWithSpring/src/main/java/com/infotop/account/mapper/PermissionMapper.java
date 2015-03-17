/**
 * 
 */
package com.infotop.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.infotop.account.model.Permission;
import com.infotop.common.PageHelper;

/**
 * @Description: TODO
 * @author wgw
 * 创建时间：2012-3-2 下午02:14:24   
 */
public interface PermissionMapper {

	List<Permission> datagridPermission(@Param("pid") String pid);
	
	List<String> getPermissionByRoleId(Long id);

	Permission findPermissionByValue(String i);

	List<Permission> getAllPermission();

	String getPermissionValueById(String id);

	void saveRolePermission(@Param("id") Long id,@Param("value") String value);

	Permission getPermissionById(long id);

	void savePermission(Permission permission);

	void updatePermission(Permission permission);

	
	
	
	
	

}
