package com.infotop.account.model;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
	private static final long serialVersionUID = -2726709540069876682L;
	
	private Long id;
	/**
	 * 父菜单id
	 */
	private Long parent_id;
	private String name;
	private String image;
	private String url;
	private String qtip;
	private Integer sort_num;
	private String description;
	/**
	 * 父菜单名称
	 */
	private String parent_menu;
	/**
	 * true:默认为叶子结点，即子菜单
	 */
	private boolean leaf = true;
	private List<Menu> children;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getQtip() {
		return qtip;
	}
	public void setQtip(String qtip) {
		this.qtip = qtip;
	}
	public Integer getSort_num() {
		return sort_num;
	}
	public void setSort_num(Integer sort_num) {
		this.sort_num = sort_num;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public String getParent_menu() {
		return parent_menu;
	}
	public void setParent_menu(String parent_menu) {
		this.parent_menu = parent_menu;
	}
}