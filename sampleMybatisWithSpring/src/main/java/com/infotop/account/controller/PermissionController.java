package com.infotop.account.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import net.infotop.util.OperationNoUtil;
import net.infotop.web.easyui.Message;
import net.infotop.web.easyui.Tree;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.classic.Logger;

import com.infotop.account.model.Permission;
import com.infotop.account.model.User;
import com.infotop.account.service.AccountService;
import com.infotop.account.service.PermissionService;
import com.infotop.account.service.AuthenticationRealm.ShiroUser;
import com.infotop.common.BasicController;


@Controller
@RequestMapping(value = "/permission")
public class PermissionController extends BasicController{
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
    public String userList(Model model) {
		ShiroUser su = super.getLoginUser();
		User user = accountService.findUserByName(su.getLoginName());
		if (user != null) {
		}else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			return "redirect:/login";
		}
		 return "account/permission/permissionList";
    }
	
	@RequestMapping(value = "treeList", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> treeList(@RequestParam(value = "id", defaultValue = "0") String parentId,
			ServletRequest request) {
		return permissionService.getPermissionTreeList(parentId);
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model,
			@RequestParam(value = "pid", defaultValue = "0") int pid) {
		Permission entity = new Permission();
		entity.setPid(pid);
		Permission temp = permissionService.getPermissionById((long) pid);
		if (temp != null)
			model.addAttribute("pName", temp.getName());
		entity.setCkey(OperationNoUtil.getUUID());
		model.addAttribute("permission", entity);
		model.addAttribute("action", "create");
		return "account/permission/permissionForm";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public Message create(@Valid Permission permission,
			RedirectAttributes redirectAttributes, ServletRequest request) {
		if (permission.getPid() == 0) {
			permission.setPkey("0");
		} else {
			Permission temp = permissionService.getPermissionById((long) permission
					.getPid());
			permission.setPkey(temp.getCkey());
		}
		permissionService.savePermission(permission);
		msg.setSuccess(true);
		msg.setMessage("权限增加成功");
		msg.setData("");
		return msg;
	}
			
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Permission permission = permissionService.getPermissionById(id);
		Permission temp = permissionService.getPermissionById((long) permission.getPid());
		if (temp != null)
			model.addAttribute("pName", temp.getName());
		model.addAttribute("permission", permission);
		model.addAttribute("action", "update");
		return "account/permission/permissionForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(
			@Valid Permission permission,
			RedirectAttributes redirectAttributes, ServletRequest request) {
		permissionService.updatePermission(permission);
		msg.setSuccess(true);
		msg.setMessage("权限更新成功");
		msg.setData(permission);
		return msg;
	}

}
