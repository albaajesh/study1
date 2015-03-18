package com.infotop.account.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.infotop.web.easyui.Tree;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.infotop.account.mapper.PermissionMapper;
import com.infotop.account.model.Permission;
import com.infotop.account.model.User;
import com.infotop.common.PageHelper;




@Component
public class PermissionService {
	@Resource
	private PermissionMapper permissionMapper;

	public List<String> getPermissionByRoleId(Long id) {
		// TODO Auto-generated method stub
		return permissionMapper.getPermissionByRoleId(id);
	}
	
	

	public String getPids(List<String> values) {
		List<Permission> permissionList = getPermissionListByValue(values);
		List<String> ids = Lists.newArrayList();
		if (permissionList != null && permissionList.size() > 0) {
			for (Permission permission : permissionList) {
				if (permission != null)
					ids.add(permission.getId() + "");
			}
		}
		return StringUtils.join(ids, ",");
	}

	public List<Permission> getPermissionListByValue(List<String> values) {
		List<Permission> permissionList = Lists.newArrayList();
		if (values != null && values.size() > 0) {
			for (String i : values) {
				permissionList.add(this.permissionMapper.findPermissionByValue(i));
			}
		}
		return permissionList;
	}



		public List<Tree> permissionTree() {
		List<Permission> l = (List<Permission>) permissionMapper.getAllPermission();
		List<Tree> lt = Lists.newArrayList();
		if (l != null && l.size() > 0) {
			for (Permission t : l) {
				Tree tree = new Tree();
				tree.setId(t.getId() + "");
				if (StringUtils.equals(t.getPermissionType(), "1")) {
					tree.setText(t.getName() + "[系统]");
				} else if (StringUtils.equals(t.getPermissionType(), "2")) {
					tree.setText(t.getName() + "[菜单]");
				} else if (StringUtils.equals(t.getPermissionType(), "3")) {
					tree.setText(t.getName() + "[功能]");
				} else {

					tree.setText(t.getName());
				}
				tree.setState("open");
				tree.setIconCls("status_online");
				tree.setPid(t.getPid() + "");
				lt.add(tree);
			}
		}
		return lt;
	}
		
		public List<Tree> getPermissionTreeList(String parentId) {
			PageHelper page = null;
			List<Tree> treeList = Lists.newArrayList();
			List<Permission> pl = datagridPermision(parentId);
			System.out.println("iiiiiiiiiiiiiiiiiiiiiii"+pl);
			if(pl!=null && pl.size()>0){
				for(Permission p:pl){
					Map<String, String> other = Maps.newHashMap();
					Tree tree = new Tree();
					tree.setId(p.getId() + "");
					tree.setPid(p.getPid() + "");
					if(p.getPid()>0){
						tree.set_parentId(p.getPid() + "");
					}	
					tree.setText(p.getName());
					tree.setIconCls("status_online");
					other.put("permissionType", p.getPermissionType());
					other.put("value", p.getValue());
					tree.setAttributes(other);
					treeList.add(tree);
				}
			}
			return treeList;
		}

		public List<Permission> datagridPermision(String pid) {
		/*	page.setStart((page.getPage()-1)*page.getRows());
			page.setEnd(page.getPage()*page.getRows());*/
			return permissionMapper.datagridPermission(pid);  
		}


		public String getPermissionValueById(String pids) {
			return permissionMapper.getPermissionValueById(pids);
			
		}



		public void saveRolePermission(Long id, String value) {
			permissionMapper.saveRolePermission(id,value);
			
		}



		public Permission getPermissionById(long id) {
			// TODO Auto-generated method stub
			return permissionMapper.getPermissionById(id);
		}



		public void savePermission(Permission permission) {
			permissionMapper.savePermission(permission);
			
		}



		public void updatePermission(Permission permission) {
			permissionMapper.updatePermission(permission);
			
		}



		public void deletePermission(Long id) {
			permissionMapper.deletePermission(id);
			
		}



		
}
