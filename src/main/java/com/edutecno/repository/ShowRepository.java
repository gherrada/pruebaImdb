package com.edutecno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutecno.model.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

}
