package com.infotop.account.controller;

import java.util.List;

import javax.annotation.Resource;

import net.infotop.web.easyui.DataGrid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;






import ch.qos.logback.classic.Logger;

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
	
	@RequestMapping(value = "authorize/{id}", method = RequestMethod.GET)
	public String authorizeForm(@PathVariable("id") Long id, Model model) {
		User user = accountService.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("action", "authorize");
		return "account/user/userAuthorizeForm";
	}

}
