package com.infotop.account.service;

import java.util.List;

import javax.annotation.Resource;






import com.infotop.account.mapper.EmployeeMapper;
import com.infotop.account.model.Employee;
import com.infotop.common.PageHelper;

public class EmployeeService {
	@Resource
	private EmployeeMapper employeeMapper;
	
	public Long getDatagridTotal(Employee employee) {
		return employeeMapper.getDatagridTotal(employee);
	}
	
	public List<Employee> datagridEmployee(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return employeeMapper.datagridEmployee(page);  
	}

}
