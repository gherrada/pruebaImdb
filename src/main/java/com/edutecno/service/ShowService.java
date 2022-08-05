package com.edutecno.service;

import java.util.List;

import com.edutecno.model.Show;

public interface ShowService {
	public List<Show>findAll();
	public Show findById(Long id);
	
	public Show save(Show show);
	public void delete(Long id);
}
