package com.infotop.account.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

import net.infotop.web.easyui.DataGrid;
import net.infotop.web.easyui.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.infotop.account.service.EmployeeService;
import com.infotop.common.BasicController;
import com.infotop.common.PageHelper;


@Controller
@RequestMapping(value = "/employee")
public class EmployeeController extends BasicController {
	
	@Autowired
	private EmployeeService employeeService;
	@Resource
	private AccountService accountService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String list(Model model){
		ShiroUser su = super.getLoginUser();
		User user = accountService.findUserByName(su.getLoginName());
		if (user != null) {
			
		}else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			return "redirect:/login";
		}
		return "employee/employeeList";
	}
	
	@ResponseBody
	@RequestMapping(value="/findList", method = RequestMethod.POST)
	public DataGrid datagrid(PageHelper page,Employee employee) {
			DataGrid dg = new DataGrid();
			ShiroUser su = super.getLoginUser();
			User user = accountService.findUserByName(su.getLoginName());
			if (user != null) {
				dg.setTotal(employeeService.getDatagridTotal(employee));
				List<Employee> employeeList = employeeService.datagridEmployee(page);
				dg.setRows(employeeList);
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
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("action", "create");
		}else {
			logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
			return "redirect:/login";
		}
		return "employee/employeeForm";
	}
	
	 @RequestMapping(value = "create", method = RequestMethod.POST)
	 @ResponseBody
	 public Message create(@Valid Employee employee, RedirectAttributes redirectAttributes) {
		try {
		ShiroUser su = super.getLoginUser();
		User user = accountService.findUserByName(su.getLoginName());
		if (user != null) {
		employeeService.save(employee);
		msg.setSuccess(true);
		msg.setMessage("信息添加成功");
		msg.setData(employee);
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
	 
	 @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	 public String updateForm(@PathVariable("id") Long id, Model model) {
		 ShiroUser su = super.getLoginUser();
		 User user = accountService.findUserByName(su.getLoginName());
		 if (user != null) {
		 Employee employee = employeeService.getEmployeeById(id);
		 model.addAttribute("employee", employee);
		 model.addAttribute("action", "update");
		 }else {
				logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
				return "redirect:/login";
			}
		 return "employee/employeeForm";
	 }
	 
	 @RequestMapping(value = "update", method = RequestMethod.POST)
	 @ResponseBody
	 public Message update(@Valid Employee employee, RedirectAttributes redirectAttributes) {
		try {
			ShiroUser su = super.getLoginUser();
			User user = accountService.findUserByName(su.getLoginName());
			if (user != null) {
		employeeService.update(employee);
		msg.setSuccess(true);
		msg.setMessage("信息添加成功");
		msg.setData(employee);
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
	 
	 @RequestMapping(value = "delete", method = RequestMethod.POST)
	 @ResponseBody
	  public Message delete(@RequestParam(value = "ids") List<Long> ids,
	            ServletRequest request) throws Exception {
		 try {
		 ShiroUser su = super.getLoginUser();
			User user = accountService.findUserByName(su.getLoginName());
			if (user != null) {
		 	employeeService.delete(ids);
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
	 
	 
	 @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	    public String view(@PathVariable("id") Long id, Model model) {
		 ShiroUser su = super.getLoginUser();
		 User user = accountService.findUserByName(su.getLoginName());
		 if (user != null) {
		 Employee employee = employeeService.getEmployeeById(id);
		 model.addAttribute("employee", employee);
		 }else {
				logger.log(this.getClass(),Logger.ERROR_INT,"登陆帐号无效!","",null);
				return "redirect:/login";
			}
		 return "employee/employeeView";
	 }

}
