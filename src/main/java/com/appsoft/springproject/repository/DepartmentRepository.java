package com.appsoft.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.springproject.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	
}
