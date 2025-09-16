package com.appsoft.springproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.appsoft.springproject.model.Employee;
import com.appsoft.springproject.model.Product;
import com.appsoft.springproject.repository.ProductRepository;
import com.appsoft.springproject.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private EmployeeService empService;// every thing is working through database.
	@GetMapping("/api/emp/list")
	public List<Employee> getAll() {
		
		return empService.getAllEmps();
	}

	@GetMapping("/api/emp/{id}")
	public Employee getOne(@PathVariable("id") Long id) {
		return empService.getEmpById(id);
	}
	
	@PostMapping("/api/emp/add")// can't post directly from the browser so we use postman app to test the api of our project.
	public String add(@RequestBody Employee emp) {// to map JSON data into object through request body.
		
		empService.addEmp(emp);
		return "added Success";
	}
	
	@DeleteMapping("/api/emp/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		
		empService.deleteEmp(id);
		
		return "delete success";
	}
	
	@PutMapping("/api/emp/update")
	public String update(@RequestBody Employee emp) {
		empService.updateEmp(emp);
		return "update success";
	}
	
	@GetMapping("/api/emp/j2o")
	public String jsonToObject() {
		
		RestTemplate rest= new RestTemplate();// to convert into object this is inbuilt class
		Employee emp= rest.getForObject("http://localhost:8080/api/emp/1", Employee.class);
		return "FirstName="+emp.getFname();
	}
	
	@GetMapping("/api/emp/ja2oa")
	public String jsonArrayToObjArray() {
		RestTemplate temp= new RestTemplate();
		Employee[] emps= temp.getForObject("http://localhost:8080/api/emp/list", Employee[].class);
		
		return "Name="+emps[2].getFname()+emps[0].getLname();
	}
	
	@GetMapping("/api/load")
	public 	String productApi() {
		RestTemplate temp= new RestTemplate();
		Product[] prods= temp.getForObject("https://fakestoreapi.com/products", Product[].class);
		
		prodRepo.saveAll(List.of(prods));
		return "api call success";
		
	}
}

