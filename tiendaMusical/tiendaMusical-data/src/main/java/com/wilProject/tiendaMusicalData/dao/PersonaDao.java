package com.wilProject.tiendaMusicalData.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wilProject.tiendaMusicalEntities.entities.Persona;

public interface PersonaDao extends PagingAndSortingRepository<Persona, Long>{

	@Query("SELECT p FROM Persona p WHERE p.usuario = ?1 AND p.password = ?2")
	Persona findByUsuarioAndPassword(String usuario, String password);
	
}
