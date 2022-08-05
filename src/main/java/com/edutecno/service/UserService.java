package com.edutecno.service;

import java.util.List;

import com.edutecno.model.Users;

public interface UserService {
	public List<Users>findAll();
	public Users findByEmail(String email);
	public Users save(Users user);
}
