package com.appsoft.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.springproject.model.Employee;
import com.appsoft.springproject.service.DepartmentService;
import com.appsoft.springproject.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;
	@GetMapping("/employee")
	public String getEmployee(Model model) {
		model.addAttribute("dList", deptService.getAllDepts());
		return "EmployeeForm";
	}
	
	@PostMapping("/employee")
	public String postEmployee(@ModelAttribute Employee emp) {
		empService.addEmp(emp);
		return "redirect:/employee";
	}
	
	@GetMapping("/employeeList")
	public String getAll(Model model) {
		model.addAttribute("eList",empService.getAllEmps());
		return "EmployeeListForm";
	}
	
	@GetMapping("/emp/delete")
	public String delete(@RequestParam("id") Long id) {
		empService.deleteEmp(id);
		return "redirect:/employeeList";
	}
	
	@PostMapping("/emp/update")
	public String updateEmployee() {
		
		return "EmployeeListForm";
	}

	
}
