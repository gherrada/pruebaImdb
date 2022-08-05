package com.edutecno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutecno.model.Rating;
import com.edutecno.repository.RatingRepository;

@Service
public class RatingServiceImp implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Rating> findAll() {	
		return ratingRepository.findAll();
	}
	@Transactional
	public void save(Rating unRating){
		ratingRepository.save(unRating);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> findUserAndRatingByShow(Long idShow) {
		
		return ratingRepository.findUserAndRatingByShow(idShow);
	}
	

	
}
