package com.appsoft.springproject.service;

import com.appsoft.springproject.model.User;

public interface UserService {

	void signup(User u);// in arguments we should put things that we need to pass in the forms
	User login(String un, String psw);
	User findUsername(String username);
}
