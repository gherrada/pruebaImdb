package com.edutecno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutecno.model.Show;
import com.edutecno.repository.ShowRepository;
@Service
public class ShowServiceImp implements ShowService {

	@Autowired
	private ShowRepository showRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Show> findAll() {
		return showRepository.findAll();
	}
	@Transactional(readOnly=true)
	@Override
	public Show findById(Long id) {
		
		return showRepository.findById(id).get();
	}
	@Transactional
	@Override
	public Show save(Show show) {
		
		return showRepository.save(show);
	}
	@Transactional
	@Override
	public void delete(Long id) {
		showRepository.deleteById(id);
		
	}
	


}
