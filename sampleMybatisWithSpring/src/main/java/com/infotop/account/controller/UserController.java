package com.infotop.account.controller;

import java.util.List;

import javax.annotation.Resource;

import net.infotop.web.easyui.DataGrid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




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
		if(user!=null){
			System.out.println("inside------"+accountService.getDatagridTotal(user));
		}else{
			return "redirect:/login";
		}
		System.out.println("insides------"+accountService.getDatagridTotal(user));
        return "account/user/userList";
    }
	
	
	@ResponseBody
	@RequestMapping(value="/userDatagrid", method = RequestMethod.POST)
	public DataGrid datagrid(PageHelper page,User user) {
		DataGrid dg = new DataGrid();
		ShiroUser su = super.getLoginUser();
			user = accountService.findUserByName(su.getLoginName());
			if (user != null) {
				dg.setTotal(accountService.getDatagridTotal(user));
				System.out.println("Size----"+accountService.getDatagridTotal(user));
				List<User> userList = accountService.datagridUser(page);
				dg.setRows(userList);
			}else {
				System.out.println("Login---");
			}
		return dg;
	}
	

}
