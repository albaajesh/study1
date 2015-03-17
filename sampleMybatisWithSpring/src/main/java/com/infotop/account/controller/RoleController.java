package com.infotop.account.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

import net.infotop.web.easyui.DataGrid;
import net.infotop.web.easyui.Message;
import net.infotop.web.easyui.Tree;

import org.apache.commons.lang3.StringUtils;
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

import com.infotop.account.model.Role;
import com.infotop.account.model.User;
import com.infotop.account.service.AccountService;
import com.infotop.account.service.AuthenticationRealm.ShiroUser;
import com.infotop.account.service.PermissionService;
import com.infotop.common.BasicController;
import com.infotop.common.PageHelper;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BasicController {
	@Resource
	private AccountService accountService;
	@Resource
	private PermissionService permissionService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
    public String userList(Model model) {
		ShiroUser su = super.getLoginUser();
		User user = accountService.findUserByName(su.getLoginName());
		if (user != null) {
		}else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			return "redirect:/login";
		}
		 return "account/role/roleList";
    }
	
	@ResponseBody
	@RequestMapping(value="/findList", method = RequestMethod.POST)
	public DataGrid datagrid(PageHelper page,Role role) {
		DataGrid dg = new DataGrid();
		ShiroUser su = super.getLoginUser();
		User user = accountService.findUserByName(su.getLoginName());
		if (user != null) {
			dg.setTotal(accountService.getDatagridTotalForRole(role));
			System.out.println("Size----"+accountService.getDatagridTotal(user));
			List<Role> roleList = accountService.datagridRole(page);
			dg.setRows(roleList);
		} else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
		}
		return dg;
	}
	@RequestMapping(value = "create", method = RequestMethod.GET)
	 public String createForm(Model model) {
		ShiroUser su = super.getLoginUser();
		User user = accountService.findUserByName(su.getLoginName());
		if (user != null) {
			Role entity = new Role();
			model.addAttribute("role", entity);
			model.addAttribute("action", "create");
		}else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			return "redirect:/login";
		}
		return "account/role/roleForm";
			
		}
	 @RequestMapping(value = "create", method = RequestMethod.POST)
	 @ResponseBody
	 public Message create(@Valid Role role, RedirectAttributes redirectAttributes) {
		try {
		
		accountService.insertRole(role);
		msg.setSuccess(true);
		msg.setMessage("信息添加成功");
		msg.setData(role);
		
		} catch (Exception ex) {
			logger.log(this.getClass(),Logger.ERROR_INT,ex.getMessage(),super.getLoginUser().getLoginName(),null);
			msg.setSuccess(false);
			msg.setMessage(ex.getMessage());
			msg.setData("");
		}
		return msg;
	 }
	 @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	 public String updateForm(@PathVariable("id") Long id, Model model) {
		 
		 Role role = accountService.getRoleById(id);
		 model.addAttribute("role", role);
		 model.addAttribute("action", "update");
		 return "account/role/roleForm";
	 }
	 @RequestMapping(value = "update", method = RequestMethod.POST)
	 @ResponseBody
	 public Message update(@Valid Role role, RedirectAttributes redirectAttributes) {
		try {
			
		accountService.updateRole(role);
		msg.setSuccess(true);
		msg.setMessage("信息添加成功");
		msg.setData(role);
			
			} catch (Exception ex) {
				logger.log(this.getClass(),Logger.ERROR_INT,ex.getMessage(),super.getLoginUser().getLoginName(),null);
				msg.setSuccess(false);
				msg.setMessage(ex.getMessage());
				msg.setData("");
			}
		return msg;
	 }
	 @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	    public String view(@PathVariable("id") Long id, Model model) {
		 ShiroUser su = super.getLoginUser();
		 User user = accountService.findUserByName(su.getLoginName());
		 if (user != null) {
			 Role role = new Role();
			 role = accountService.getRoleById(id);
		 model.addAttribute("role", role);
		 }else {
				logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
				return "redirect:/login";
			}
		 return "account/user/userView";
	 }
	 
	@RequestMapping(value = "tree")
	@ResponseBody
	public List<Tree> tree(Model model, ServletRequest request) {
		return accountService.roleTree();
	}
	@RequestMapping(value = "/authorize/{id}", method = RequestMethod.GET)
	public String authorizeForm(@PathVariable("id") Long id, Model model) {
		Role role = accountService.getRoleById(id);
		model.addAttribute("role", role);
		List<String> getPermissionList = permissionService.getPermissionByRoleId(id);
		model.addAttribute("pids",
				permissionService.getPids(getPermissionList));
		return "account/role/roleAuthorizeForm";
	}
	@RequestMapping(value = "authorize", method = RequestMethod.POST)
	@ResponseBody
	public Message authorize(@Valid Role role,
			RedirectAttributes redirectAttributes, ServletRequest request,@RequestParam(value="id") Long id) {
		try{
		accountService.deleteRolePermissionById(id);	
		String pidstemp = role.getPermissionIds();
		String[] pids = null;
		if (StringUtils.isNotBlank(pidstemp)) {
			pids = pidstemp.split(",");
			for(int i=0;i<=pids.length;i++){
				String value = permissionService.getPermissionValueById(pids[i]);
				permissionService.saveRolePermission(id,value);
			}
			
		}
		}catch (Exception ex) {
			logger.log(this.getClass(), Logger.ERROR_INT, ex.getMessage(),
					super.getLoginUser().getLoginName(), null);
		}
		msg.setSuccess(true);
		msg.setMessage("角色更新成功");
		msg.setData(role);
		return msg;
	}
	@RequestMapping(value = "/permission/tree")
	@ResponseBody
	public List<Tree> tree() {
		return permissionService.permissionTree();
	}
}
