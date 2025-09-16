package com.appsoft.springproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.springproject.model.User;
import com.appsoft.springproject.repository.UserRepository;
import com.appsoft.springproject.service.UserService;

@Service // controller will give data to service for processing before sending or calling database. Mainly used for business logics
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

@Override
public void signup(User u) {
	userRepo.save(u);//save function is already in the jpa repository 
	
}


@Override
public User login(String un, String psw) {
	return userRepo.findByUsernameAndPassword(un, psw);// this is created function in the repository
	
}


@Override
public User findUsername(String username) {
	
	return userRepo.findByUsername(username);
}




}
