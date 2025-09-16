package com.appsoft.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.springproject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
