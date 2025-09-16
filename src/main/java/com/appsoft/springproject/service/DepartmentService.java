package com.appsoft.springproject.service;

import java.util.List;

import com.appsoft.springproject.model.Department;

public interface DepartmentService {

	void addDept(Department dept);
	void deleteDept(int id);
	void updateDept(Department dept);
	Department getDeptById(int id);
	List<Department> getAllDepts();
	
	
}
