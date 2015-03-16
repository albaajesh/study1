package com.infotop.account.service;

import java.util.List;

import javax.annotation.Resource;








import org.springframework.stereotype.Component;

import com.infotop.account.mapper.EmployeeMapper;
import com.infotop.account.model.Employee;
import com.infotop.common.PageHelper;

@Component
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
	
	public void save(Employee employee){
		employeeMapper.insert(employee);
	}
	
	public void update(Employee employee){
		employeeMapper.update(employee);
	}
	
	public Employee getEmployeeById(Long id){
		return employeeMapper.getEmployeeById(id);
	}

	public void delete(List<Long> ids) {
		for(Long id:ids){
			employeeMapper.delete(id);
		}
		
		// TODO Auto-generated method stub
		
	}

}
