package com.appsoft.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.springproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsernameAndPassword(String un, String psw);// we make this method because it doesnot have method for the login.
	User findByUsername(String username);
}
