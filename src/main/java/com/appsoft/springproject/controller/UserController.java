package com.appsoft.springproject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.springproject.model.Product;
import com.appsoft.springproject.model.User;
import com.appsoft.springproject.repository.ProductRepository;
import com.appsoft.springproject.service.UserService;
import com.appsoft.springproject.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private ProductRepository prodRepo;
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("prodList", prodRepo.findAll());
		return "CustomerHome";
	}

	@GetMapping({"/login"})
	public String getLogin() {
		log.info("-----user login info--------");//by default log is object while using lombok.
		return "LoginForm";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		return "SignupForm";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User u, Model model) {
		User existingUser= userService.findUsername(u.getUsername());
		
		if(existingUser!=null) {
			model.addAttribute("signuperror","This username already exists try another one");
			return "SignupForm";
		}
		userService.signup(u);
		return "LoginForm";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute User u, Model model, HttpSession session,@RequestParam("g-recaptcha-response") String gcode) throws IOException {
		
		if(VerifyRecaptcha.verify(gcode)) {
			
		
		log.info("----user login page-----");
		
		User usr= userService.login(u.getUsername(), u.getPassword());
		if(usr!= null) {
			session.setAttribute("activeuser", usr);// usr is the object
			session.setMaxInactiveInterval(800);//in seconds
			//model.addAttribute("uname", usr.getFname());
			
			if(usr.getRole().equals("CUSTOMER")) {
				model.addAttribute("prodList", prodRepo.findAll());
				return "CustomerHome";
			}
			 return "Home";
		}
		}else {
			log.info("------login failed-----");
			model.addAttribute("error","User Not Found");
			return "LoginForm";
		}
		log.info("------login failed-----");
		model.addAttribute("error","You are a robot");
		return "LoginForm";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session ) {
		session.invalidate();//session kill/clear. once logout then cant go inside without login.
		return "LoginForm";
	}
	
	@GetMapping("/profile")
	public String getProfile() {
		return "Profile";
	}
	
	@GetMapping("/category/women")
	public String getWomen(Model model, @RequestParam("category") Product category) {
		model.addAttribute("womenList",prodRepo.getByCategory("women's clothing"));
		return "CustomerCategory";
	}

}
