package com.appsoft.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.appsoft.springproject.model.Department;
import com.appsoft.springproject.service.DepartmentService;
import com.appsoft.springproject.utils.DepartmentExcelView;
import com.appsoft.springproject.utils.DepartmentPdfView;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService deptService;// in order to send the data to the database we call service functions
	
	@GetMapping("/department")
	public String getDepartment() {
		 return "DepartmentForm";
	}
	
	@PostMapping("/department")
	public String postDepartment(@ModelAttribute Department dept) {
		deptService.addDept(dept);
		return "DepartmentForm";
	}
	
	@GetMapping("/departmentList")
	public String getAll(Model model) {
		model.addAttribute("dList",deptService.getAllDepts());
		return "DepartmentListForm";
	}
	
	@GetMapping("/dept/delete")
	public String delete(@RequestParam("id") int id) {
		deptService.deleteDept(id);
		return "redirect:/departmentList";
	}
	
	@GetMapping("/dept/edit")
	public String edit(@RequestParam("id") int id, Model model) {
		model.addAttribute("dmodel", deptService.getDeptById(id));//dmodel is an object to be shown in the html file.
		return "EditDepartmentForm";
	}
	@PostMapping("/dept/update")
	public String update(@ModelAttribute Department dept) {
		deptService.updateDept(dept);
		return "redirect:/departmentList";
	}
	
	@GetMapping("/dept/excel")
	public ModelAndView getExcel() {
		ModelAndView mv= new ModelAndView();
		mv.addObject("dList", deptService.getAllDepts());
		mv.setView(new DepartmentExcelView());
		return mv;
	}
	
	@GetMapping("/dept/pdf")
	public ModelAndView getPdf() {
		ModelAndView mv= new ModelAndView();
		mv.addObject("dList", deptService.getAllDepts());
		mv.setView(new DepartmentPdfView());
		return mv;
	}
}
