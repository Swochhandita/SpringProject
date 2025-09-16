package com.appsoft.springproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.springproject.model.Department;
import com.appsoft.springproject.repository.DepartmentRepository;
import com.appsoft.springproject.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository deptRepo;
	@Override
	public void addDept(Department dept) {
		deptRepo.save(dept);// while adding we don't send the id
		
	}

	@Override
	public void deleteDept(int id) {
		deptRepo.deleteById(id);
	}

	@Override
	public void updateDept(Department dept) {
		deptRepo.save(dept);// while updating we send id
		
	}
	
	@Override
	public Department getDeptById(int id) {
		
		return deptRepo.findById(id).get();
	}


	@Override
	public List<Department> getAllDepts() {
		
		return deptRepo.findAll();
	}

	
	
}
