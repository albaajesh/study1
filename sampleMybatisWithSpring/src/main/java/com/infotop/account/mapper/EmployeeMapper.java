package com.infotop.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.infotop.account.model.Employee;
import com.infotop.account.model.User;
import com.infotop.common.PageHelper;

public interface EmployeeMapper {
	
	public Long getDatagridTotal(Employee employee);
	public List<Employee> datagridEmployee(PageHelper page);
	Employee getEmployeeById(@Param("id") Long id);
	void insert(Employee employee);
	void update(Employee employee);
	void delete(Long id);
	

}
