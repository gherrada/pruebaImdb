package com.edutecno.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "show")
@SequenceGenerator(name="SQ_SHOW",initialValue = 1,allocationSize = 1,sequenceName = "SQ_SHOW")
@Entity
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_SHOW")
	private Long id;
	private String showTitle;
	private String showNetwork;
	
	@OneToMany(mappedBy = "show",cascade = CascadeType.ALL, orphanRemoval = true)
	List<Rating> rating = new ArrayList<>();
}
