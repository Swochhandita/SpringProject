package com.appsoft.springproject.service;

import java.util.List;

import com.appsoft.springproject.model.Employee;

public interface EmployeeService {
 
	void addEmp(Employee emp);
	void deleteEmp(Long id);
	void updateEmp(Employee emp);
	Employee getEmpById(Long id);
	List<Employee> getAllEmps();
}
