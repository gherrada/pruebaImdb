package com.edutecno.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "rating")
@SequenceGenerator(name="SQ_RATING",initialValue = 1,allocationSize = 1,sequenceName = "SQ_RATING")
@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_RATING")
	private Long id;
	private int rating;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="show_id", referencedColumnName = "id")
	private Show show;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private Users user;
}