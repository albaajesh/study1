package com.infotop.account.controller;

import java.util.List;

import javax.annotation.Resource;

import net.infotop.web.easyui.DataGrid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infotop.account.model.Employee;
import com.infotop.account.model.User;
import com.infotop.account.service.EmployeeService;
import com.infotop.common.PageHelper;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	@Resource
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model model){
		return "employee/employeeList";
	}
	
	@ResponseBody
	@RequestMapping(value="/findList", method = RequestMethod.POST)
	public DataGrid datagrid(PageHelper page,Employee employee) {
		DataGrid dg = new DataGrid();
	/*	ShiroUser su = super.getLoginUser();
			user = accountService.findUserByName(su.getLoginName());
			if (user != null) {*/
				dg.setTotal(employeeService.getDatagridTotal(employee));
				List<Employee> employeeList = employeeService.datagridEmployee(page);
				dg.setRows(employeeList);
			/*}else {
				System.out.println("Login---");
			}*/
		return dg;
	}

}
