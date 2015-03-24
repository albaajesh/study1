package com.infotop.account.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.infotop.web.easyui.Tree;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.infotop.account.mapper.ParameterMapper;
import com.infotop.account.model.Parameter;
import com.infotop.account.model.Permission;
import com.infotop.common.PageHelper;

@Component
public class ParameterService {
	@Resource
	private ParameterMapper parameterMapper;
	
	public List<Tree> getParameterTreeList(String parentId) {
		PageHelper page = null;
		List<Tree> treeList = Lists.newArrayList();
		List<Parameter> pl = parameterMapper.datagridParameter(parentId);
		System.out.println("iiiiiiiiiiiiiiiiiiiiiii"+pl);
		if (pl != null && pl.size() > 0) {
			for (Parameter p : pl) {
				Tree tree = new Tree();
				tree.setId(p.getId() + "");
				if (p.getParentId() > 0) {
					tree.setPid(p.getParentId() + "");
					tree.set_parentId(p.getParentId() + "");
				}
				tree.setText(p.getName());
				tree.setIconCls("status_online");
				tree.setState("closed");
				tree.setValue(p.getUuid());
				Map<String, String> attribute = Maps.newHashMap();
				attribute.put("category", p.getCategory());
				attribute.put("subcategory", p.getSubcategory());
				attribute.put("name", p.getName());
				attribute.put("value", p.getValue());
				attribute.put("remark", p.getRemark());
				attribute.put("sort", String.valueOf(p.getSort()));
				attribute.put("parentId", String.valueOf(p.getParentId()));
				tree.setAttributes(attribute);
				treeList.add(tree);
			}
		}
		return treeList;
	}

	public Parameter getParameterById(Long pid) {
		// TODO Auto-generated method stub
		return parameterMapper.getParameterById(pid);
	}

	public void save(Parameter parameter) {
		parameterMapper.saveParameter(parameter);
		
	}

	public void update(Parameter parameter) {
		parameterMapper.updateParameter(parameter);
		
	}

	public void deleteParameter(Long id) {
		parameterMapper.deleteParameter(id);
		
	}

}
