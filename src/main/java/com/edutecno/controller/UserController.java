package com.edutecno.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edutecno.model.Users;
import com.edutecno.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	//metodos para login y registro
	
	@Autowired
	private UserService userService;
	
	/*Al iniciar el proyecto redirijo a login,
	 *  Al mismo tiempo, si alguien accede a la ruta de login, 
	 *  tambien cargo este controlador 
	 */ 
	@GetMapping({"/","/login"})
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("userLogin",new Users());
		log.info("controlador login terminado, redirigiendo a la vista");
		return modelAndView;
	}
	
	/*Procesa los datos de sesion
	 * Leo la base de datos, veo si tiene o no permisos.
	 * 
	 */
//	@PostMapping("/loginForm")
//	public ModelAndView loginForm(@ModelAttribute("userLogin") Users userLogin) {
//		ModelAndView modelAndView = new ModelAndView();
//		
//		log.info("Ingresando al controlador formulario loginForm");
//		modelAndView.addObject("userLogin",new Users());
//		log.info("enviando datos del usuario a la vista");
//		return new ModelAndView("login");
//	}
	

	
	/*Metodo para el registro
	 * Obtiene datos desde formularios de registration
	 * crea un nuevo user llamando a servicio
	 * quien llama a repositorio
	 * Y agrega el usuario a la base de datos
	 */
	
	/*Metodo llamado desde login por el link registration en navbar
	 * Crea un objeto User para ser usado en el registro
	 * 
	 */
	@GetMapping("/registroForm")
	public ModelAndView registroForm() {
		
		log.info("ingreso a pagina de registro");
		ModelAndView modelAndView = new ModelAndView("registration");
		modelAndView.addObject("nuevoUsuario",new Users());
		
		log.info("agregado nuevoUsuario y redirige a pagina registration");
		return modelAndView;
	}
	
	/* Recibe el modelo desde el formulario de registro
	 * el User es llamado nuevoUsuario
	 * luego de completar el registro
	 * redirige a la vista login donde se muestra un mensaje
	 * para informar que el registro ha sido exitoso 
	 * Agrega un role a usuario.  Por defecto es USER.
	 */
	@PostMapping("/registro")
	public ModelAndView registro(@ModelAttribute("nuevoUsuario") Users nuevoUsuario) {
		ModelAndView modelAndView = new ModelAndView("login");
		
		log.info("en el controlador registro, validando password");
		//validar la password para el registro
		//no se puede comparar directamente un string con =
		if(!nuevoUsuario.getPassword().equals(nuevoUsuario.getPasswordConfirmation())) {
			modelAndView.addObject("passwordError",1);
			log.info("Error, la password no coincide con passwordConfirmation");
			log.info("Password es : "+nuevoUsuario.getPassword());
			log.info("passwordConfirmation es : "+nuevoUsuario.getPasswordConfirmation());
			return modelAndView;
		}
		
		log.info("encriptando password");
		String encodedPassword = new BCryptPasswordEncoder().encode(nuevoUsuario.getPassword());
		nuevoUsuario.setPassword(encodedPassword);
		
		log.info("encriptando password confirmation");
		String encodedPasswordConfirmation = new BCryptPasswordEncoder().encode(nuevoUsuario.getPasswordConfirmation());
		nuevoUsuario.setPasswordConfirmation(encodedPasswordConfirmation);
		
		log.info("guardando usuario nuevo");
		//tomo el usuario y lo guardo en la base de datos
		userService.save(nuevoUsuario);
		
		log.info("usuario registrado exitosamente, volviendo a login");

		//enviar una confirmacion del registro a la vista login.
		modelAndView.addObject("mensaje",0);
		
		return modelAndView;
	}
}
