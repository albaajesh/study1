package com.infotop.account.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

import net.infotop.web.easyui.DataGrid;
import net.infotop.web.easyui.Message;

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

import com.infotop.account.model.Employee;
import com.infotop.account.model.User;
import com.infotop.account.service.AccountService;
import com.infotop.account.service.AuthenticationRealm.ShiroUser;
import com.infotop.common.BasicController;
import com.infotop.common.PageHelper;



@Controller
@RequestMapping(value = "/account")
public class UserController extends BasicController {
	@Resource
	private AccountService accountService;
	
	@RequestMapping(value = "/user",method = RequestMethod.GET)
    public String userList(Model model) {
		ShiroUser su = super.getLoginUser();
		User user = accountService.findUserByName(su.getLoginName());
		if (user != null) {
		}else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			return "redirect:/login";
		}
		 return "account/user/userList";
    }
	
	
	@ResponseBody
	@RequestMapping(value="/findList", method = RequestMethod.POST)
	public DataGrid datagrid(PageHelper page,User user) {
		DataGrid dg = new DataGrid();
		ShiroUser su = super.getLoginUser();
		user = accountService.findUserByName(su.getLoginName());
		if (user != null) {
				dg.setTotal(accountService.getDatagridTotal(user));
				System.out.println("Size----"+accountService.getDatagridTotal(user));
				List<User> userList = accountService.datagridUser(page);
				dg.setRows(userList);
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
			User entity = new User();
			model.addAttribute("user", entity);
			model.addAttribute("action", "create");
		}else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			return "redirect:/login";
		}
		return "account/user/register";
			
		}
	 @RequestMapping(value = "create", method = RequestMethod.POST)
	 @ResponseBody
	 public Message create(@Valid User user, RedirectAttributes redirectAttributes) {
		try {
		
		accountService.saveUser(user);
		msg.setSuccess(true);
		msg.setMessage("信息添加成功");
		msg.setData(user);
		
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
		 
		 User user = accountService.getUserUpdateById(id);
		 model.addAttribute("user", user);
		 model.addAttribute("action", "update");
		 return "account/user/userForm";
	 }
	 
	 @RequestMapping(value = "update", method = RequestMethod.POST)
	 @ResponseBody
	 public Message update(@Valid User user, RedirectAttributes redirectAttributes) {
		try {
			
		accountService.updateUser(user);
		msg.setSuccess(true);
		msg.setMessage("信息添加成功");
		msg.setData(user);
			
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
			 User entity = new User();
			 entity = accountService.getUserUpdateById(id);
		 model.addAttribute("user", entity);
		 }else {
				logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
				return "redirect:/login";
			}
		 return "account/user/userView";
	 }
	 @RequestMapping(value = "delete", method = RequestMethod.POST)
	 @ResponseBody
	  public Message delete(@RequestParam(value = "ids") Long id,
	            ServletRequest request) throws Exception {
		 try {
		 ShiroUser su = super.getLoginUser();
			User user = accountService.findUserByName(su.getLoginName());
			if (user != null) {
		 	accountService.delete(id);
		 	msg.setSuccess(true);
			msg.setMessage("信息删除成功");
			msg.setData("");
	 }else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			msg.setSuccess(false);
			msg.setMessage("登陆帐号无效!");
			msg.setData("");
			}
		} catch (Exception ex) {
			logger.log(this.getClass(),Logger.ERROR_INT,ex.getMessage(),super.getLoginUser().getLoginName(),null);
			msg.setSuccess(false);
			msg.setMessage(ex.getMessage());
			msg.setData("");
		}
			return msg;
	 }
	@RequestMapping(value = "authorize/{id}", method = RequestMethod.GET)
	public String authorizeForm(@PathVariable("id") Long id, Model model) {
		User user = accountService.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("action", "authorize");
		return "account/user/userAuthorizeForm";
	}
	
	@RequestMapping(value = "authorize", method = RequestMethod.POST)
	@ResponseBody
	public Message authorize(@Valid User user,@RequestParam(value="id") Long id) {
		String checkedRoleList = user.getRoleIds();
		accountService.deleteUserRoleByUserId(id);
		if (StringUtils.isNotBlank(checkedRoleList)) {
			for (String roleId : checkedRoleList.split(",")) {
				accountService.insertUserRole(id,Long.parseLong(roleId));
			}
			
		}
		msg.setSuccess(true);
		msg.setMessage("更新用户" + user.getLoginName() + "成功");
		msg.setData(user);
		return msg;
	}

}
