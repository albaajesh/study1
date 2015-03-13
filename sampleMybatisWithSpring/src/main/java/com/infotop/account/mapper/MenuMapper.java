package com.infotop.account.mapper;

import java.util.List;
import java.util.Map;

import com.infotop.account.model.Menu;



public interface MenuMapper {
	List<Menu> getMainMenuList(Long userId);
	List<Menu> getSubMenuList(Map<String,Object> param);
	List<Menu> getMenuList();
	List<Menu> getMenus();
	List<Menu> getParentMenus();
	Long getId();
	void insertMenu(Menu menu);
	void updateMenu(Menu menu);
	void deleteMenu(Map<String,Object> param);
}
