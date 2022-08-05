package com.edutecno.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "users")
@SequenceGenerator(name="SQ_USERS",initialValue = 1,allocationSize = 1,sequenceName = "SQ_USERS")
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_USERS")
	private Long id;
	private String username;
	private String email;
	private String password;
	private String passwordConfirmation;
	
	@OneToMany(mappedBy = "user")
	List<Rating> rating = new ArrayList<>();

	//si role es enum
	private Role role;
	
// Si role es clase.  
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="role_id",referencedColumnName = "id")
//	private Role role;

	

}
