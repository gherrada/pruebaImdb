package com.edutecno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edutecno.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	/*
	 * query para listar todos los rating con su respectivo email del usuario
	 * para un show  determinado por un id
	 * Para extraer con JPA, pediremos users.mail y rating.rating
	 * 
	 */

	@Query(value= "SELECT u.email ,r.rating "
			+"From users u "
			+ "JOIN rating r on u.id = r.user_id "
			+"where r.show_id=?1",nativeQuery=true)
	List<Object[]> findUserAndRatingByShow(Long id);


//jpa crea findAll por defecto
}
