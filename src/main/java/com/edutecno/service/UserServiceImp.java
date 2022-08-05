package com.edutecno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutecno.model.Role;
import com.edutecno.model.Users;
import com.edutecno.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Users> findAll() {
		return userRepository.findAll();
	}
	@Transactional
	public Users save(Users user) {
		
		//si no tiene un role asignar USER
		if(user.getRole()==null) {
			user.setRole(Role.USER);
		}


		System.out.println("usuario registrado");
		return userRepository.save(user);
	}
	@Transactional
	@Override
	public Users findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

}
