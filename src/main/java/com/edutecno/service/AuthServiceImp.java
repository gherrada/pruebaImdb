package com.edutecno.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edutecno.model.Role;
import com.edutecno.model.Users;

/* La clase AuthServiceImp establece permisos para un usuario segun su role, y revisa a traves
 * del servicio UserService si el usuario existe en la base de datos
 * 
 * 
 */
@Service
public class AuthServiceImp implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Users user = userService.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException(email);
		}else {
			
		}
		List<GrantedAuthority>authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
		
		//crea un User de UserDetails con los datos para manejar el login
		//notar que usa los datos quee estan en el usuario que ha leido desde la base de datos
		return new User(user.getEmail(),user.getPassword(),authorities);
		
	}
	
	/* Para el registro se requiere asignar un rol
	 * 
	 */
	public void SetRole(Users user, Role role) {
		if(user.getRole()==null) {
			user.setRole(Role.USER);
		}
	}

}
