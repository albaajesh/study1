package com.infotop.account.service;

import java.util.List;

import javax.annotation.Resource;

import net.infotop.web.easyui.Tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;






import com.google.common.collect.Lists;
import com.infotop.account.mapper.RoleMapper;
import com.infotop.account.mapper.UserMapper;
import com.infotop.account.model.Role;
import com.infotop.account.model.User;
import com.infotop.common.PageHelper;

@Component
public class AccountService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	public User findUserByName(String username) {
		return userMapper.getUser(username);
	}

	public Role findRoleByName(String roleName){
		return roleMapper.getRole(roleName);
	}
	
	public Long getDatagridTotal(User user) {
		return userMapper.getDatagridTotal(user);  
	}
	
	public List<User> datagridUser(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return userMapper.datagridUser(page);  
	}
	
	public Long getDatagridTotalForRole(Role role) {
		return roleMapper.getDatagridTotalForRole(role);  
	}
	
	public List<Role> datagridRole(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return roleMapper.datagridRole(page);  
	}
	public User getUserById(Long id){
		return userMapper.getUserById(id);
	}
	public List<Role> getAllRole(){
		return roleMapper.getAllRole();
	}
	public List<Tree> roleTree() {
		List<Role> l = (List<Role>) roleMapper.getAllRole();
		List<Tree> lt = Lists.newArrayList();
		if (l != null && l.size() > 0) {
			for (Role t : l) {
				Tree tree = new Tree();
				tree.setId(t.getId() + "");
				tree.setText(t.getName());
				tree.setState("open");
				tree.setIconCls("status_online");
				if (t.getPid() > 0) {
					tree.setPid(t.getPid() + "");
				}
				lt.add(tree);
			}
		}
		return lt;
	}

	public Role getRoleById(Long id) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleById(id);
	}

	public void deleteRolePermissionById(Long id) {
		roleMapper.deleteRolePermissionById(id);
		
	}
}
