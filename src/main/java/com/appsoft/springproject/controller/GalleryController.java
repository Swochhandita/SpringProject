package com.appsoft.springproject.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.appsoft.springproject.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class GalleryController {
	
	@Autowired
	private ProductRepository prodRepo;

	@GetMapping("/gallery")
	public String getGallery(Model model, HttpSession session) {
		
		if(session.getAttribute("activeuser")==null) {
			return "LoginForm";
		}
		String[] imgNames= new File("src/main/resources/static/images").list();
		model.addAttribute("imgList",imgNames);
		return "GalleryForm";
	}
	
	@GetMapping("/productGallery")//url
	public String productGallery(Model model) {
		model.addAttribute("prodList", prodRepo.findAll());
		return "ProductGalleryForm";
	}

}
