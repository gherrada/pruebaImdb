package com.edutecno.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edutecno.model.Rating;
import com.edutecno.model.Show;
import com.edutecno.model.Users;

import com.edutecno.service.RatingService;
import com.edutecno.service.ShowService;
import com.edutecno.service.UserService;

/*
 * El objetivo de este controlador es permitir al usuario agregar 
 * un rating a un show. 
 *	
 *	Para acceder, el usuario ha iniciado sesion desde login
 *	y fue redirigido a /user/home 
 * 
 *  La primera vista es home. Aca lista los shows una vez que el usuario esta logeado
 *  Cada show tiene un link a su pagina de rating.
 *  La pagina de rating es show.jsp
 *  
 *  La vista para agregar shows es new.jsp
 *  El controlador recibe datos del formulario en esa vista.
 *  Y crea el objeto rating para ese show, con sus caracteristicas
 *  
 * 
 */


@Controller
public class ShowController {

	@Autowired
	private ShowService showService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private RatingService ratingService;
	
	/*Recibe al usuario luego de iniciar sesion
	 * muestra la lista de shows en home
	 * 
	 */
	@GetMapping("/user/shows")
	public ModelAndView home(HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView("user/home");
		modelAndView.addObject("showList",showService.findAll());
		
		
		return modelAndView;
	}
	
	
	/*	agrega un Show a la vista new 
	 * 	redirige a user/new
	 *
	 */
	
	@GetMapping("/newShow")
	public ModelAndView newShow() {
		ModelAndView modelAndView = new ModelAndView("user/new");
		
		modelAndView.addObject("newShow",new Show());
		return modelAndView;
	}
	
	
	/* Recive datos desde el formulario en la vista new
	 * Crea un nuevo Show en la base de datos
	 * redirige a la vista user/home
	 */
	
	@PostMapping("/addShow")
	public ModelAndView addShow(@ModelAttribute("newShow") Show show) {
		ModelAndView modelAndView = new ModelAndView("user/home");
		
		
		showService.save(show);
		
		modelAndView.addObject("showList",showService.findAll());
		return modelAndView;
	}
	
	/*
	 *  Editar un show por metodo get desde show.jsp
	 *  pasa el id como idShow
	 * 	luego redirige a la vista edit
	 */
	
	@GetMapping("/editShow")
	public ModelAndView editShow(@RequestParam("idShow")Long idShow) {
		
		ModelAndView modelAndView = new ModelAndView("user/edit");
		
		Show show = showService.findById(idShow);
		modelAndView.addObject("showEdit", show);
		return modelAndView;
	}
	
	/* Editar un show desde la vista edit.jsp por metodo post
	 * luego redirige a home 
	 */
	@PostMapping("/editForm")
	public ModelAndView editForm(@ModelAttribute("updateShow") Show show) {
		ModelAndView modelAndView = new ModelAndView("user/shows");
		
		Show editShow = showService.findById(show.getId());
		
		editShow.setShowTitle(show.getShowTitle());
		editShow.setShowNetwork(show.getShowNetwork());
		showService.save(editShow);
		
		return modelAndView;
	}
	
	
	/*
	 * Eliminar un show por metodo get
	 *  recibe un id de show como idShow
	 *  redirige a home despues de eliminar
	 */
	@GetMapping("/eliminar")
	public ModelAndView eliminarShow(@RequestParam("idShow")Long id) {
		ModelAndView modelAndView = new ModelAndView("user/home");
		showService.delete(id);
		return modelAndView;
	}
	
	
	/* La vista home envia por metodo get el id del show por un link
	 * El controlador recibe el id del show para 
	 * obtener los datos del show y su rating asociado a este usuario
	 * la relacion entre show y usuarios es muchos a muchos.
	 * La tabla rating es una tabla intermedia que conecta 
	 * a cada usuario con la lista de ratings para cada show
	 * 
	 */
	@GetMapping("/showRating")
	public ModelAndView calificar(HttpSession httpSession,@RequestParam Long idShow) {
		ModelAndView modelAndView = new ModelAndView("user/show");
		
		Show show = showService.findById(idShow);
		modelAndView.addObject("show",show);
		
		//listas para transportar datos a la vista
		
		List<String> emailList = new ArrayList<String>();
		List<Integer>ratingList = new ArrayList<Integer>();
		
		// la lista <Object[]> contiene un String en [0] y un int en [1]
		
		List<Object[]> userAndRatings = ratingService.findUserAndRatingByShow(idShow);
		
		for(Object[]userAndRating: userAndRatings) {
			String userEmail = userAndRating[0].toString();
			Integer rating = Integer.parseInt(userAndRating[1].toString());
			
			emailList.add(userEmail);
			ratingList.add(rating);
			//hacer algo
		}
		
		//agregar las listas a la vista
		modelAndView.addObject("userList",emailList);
		modelAndView.addObject("ratingList",ratingList);
		
		//recuperar el email usado para inicio de la sesion actual
		
//		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	      String name = auth.getName();
//	      modelAndView.addObject("nombre",name);
	      
		return modelAndView;
	}
	
	/*	agrega el rating a la base de datos junto al email del usuario
	 * 	la tabla rating tiene una llave foranea del usuario
	 *  Por tanto usando el email, encontrar el id del usuario. 
	 *  Y ese id se registra en la tabla rating
	 *  Ademas se necesita el id del show
	 * 
	 */
	@PostMapping("/rate")
	public ModelAndView guardarRating(@RequestParam("idShow") Long idShow,@RequestParam("rating") Integer rating) {
		//obtener el email de la sesion
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    //obtener al usuario desde la base de datos
		Users user = userService.findByEmail(email);
		
		//obtener al show con el id en la base de datos
		Show show = showService.findById(idShow);
		
		//crear un nuevo rating para escribir en la base de datos
		Rating unRating = new Rating();
		unRating.setRating(rating);
		unRating.setShow(show);
		unRating.setUser(user);
		
		ratingService.save(unRating);
		//dado que tengo el id del show y el usuario, puedo escribir en rating
	//	ratingService.save(rating,idShow,idUser);
		ModelAndView modelAndView = new ModelAndView("user/home");
		modelAndView.addObject("showList",showService.findAll());
		
		return modelAndView;
	}
	
}
