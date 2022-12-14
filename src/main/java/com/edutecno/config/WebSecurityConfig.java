package com.edutecno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

	//especificar estrategia de autentificacin y validar ingreso de usuario
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	//constructor vacio que instancia el private AuthenticationSuccessHandler
	public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}
	
	//manejando la autentificacion del usuario recibiendo la configuracion de autentificacion del usuario
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
//	@Bean
//	InMemoryUserDetailsManager userDetailsService() {
//
//        UserDetails user = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles("ADMIN")
//            .build();
//
//        UserDetails user2 = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user,user2);
//    }
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.csrf().disable();
		//http.headers().frameOptions().disable();
        http
        .authorizeRequests()//autorizando los request recibidos de los antMatchers y verificacion de rol
        .antMatchers("/admin/**").hasAuthority("ADMIN")
        .antMatchers("/user/**").hasAuthority("USER")
        .antMatchers("/login","/registration","/registroForm","/registro")
        .permitAll()
        .anyRequest()//request que sea recibido a /login debe ser autenticado
        .authenticated()
        .and()
        .formLogin()//definiendo cual es la pagina inicial para el login
        .loginPage("/login")//URL para la pagina de login o inicio de sesi??n
        .successHandler(authenticationSuccessHandler)//manejando la autentificacion del usuario mediante authenticationSuccessHandler
        .failureUrl("/login?error=true")//URL para un login fallido
        .usernameParameter("email")//nombre del parametro del input en el formulario
        .passwordParameter("password")//nombre del parametro para el password en el input del formulario
        .and()
        .exceptionHandling()//si ocurre un error de ingreso se ejecuta la pagina de recurso-prohibido
        .accessDeniedPage("/recurso-prohibido")
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true);

        return http.build();
    }
	
	@Bean
	@Order(0)
	SecurityFilterChain resources(HttpSecurity http) throws Exception {
	    http
	        .requestMatchers((matchers) -> matchers.antMatchers("/images/**", "/js/**", "/webjars/**"))
	        .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
	        .requestCache().disable()
	        .securityContext().disable()
	        .sessionManagement().disable();

	    return http.build();
	}
	
//	@Bean
//	WebSecurityCustomizer webSecurityCustomizer() {
//		return (web)->web.ignoring().antMatchers("/images/**","/js/**","/webjars/**");
//	}
	
    //metodo encargado de codificar la password
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

	
