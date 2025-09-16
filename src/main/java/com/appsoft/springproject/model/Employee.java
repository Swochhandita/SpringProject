package com.appsoft.springproject.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="employee_tbl")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fname;
	private String lname;
	private String gender;
	private String phone;
	@DateTimeFormat(iso =ISO.DATE )
	private LocalDate dob;
	private String email;
	private String company;
	private String post;
	private int salary;
	@DateTimeFormat(iso =ISO.DATE )
	private LocalDate joinDate;
	@ManyToMany
	private List<Department> departments;
	@OneToOne(cascade = CascadeType.ALL)// of the data in employee is deleted then corresponding data in the address is also deleted automatically
	@JoinColumn(name="adrId")// name of the foreign key
	private Address address;
	@ElementCollection
	@CollectionTable
	private List<String> projects;// if we only need the name of the project that employee is working on then we use this line otherwise we would make pojo.

}
