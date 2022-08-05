package com.edutecno.service;

import java.util.List;

import com.edutecno.model.Rating;

public interface RatingService {
	public List<Rating>findAll();
	
	//public void saveRating(Integer rating,Long idShow,Long idUser);
	public void save(Rating unRating);

	public List<Object[]> findUserAndRatingByShow(Long idShow);
}
