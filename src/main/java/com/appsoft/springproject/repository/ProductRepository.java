package com.appsoft.springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.springproject.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> getByCategory(String category);
	

}
