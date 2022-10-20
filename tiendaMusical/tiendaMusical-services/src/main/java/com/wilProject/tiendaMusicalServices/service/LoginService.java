package com.wilProject.tiendaMusicalServices.service;

import com.wilProject.tiendaMusicalEntities.entities.Persona;

public interface LoginService {

	Persona consultarUsuarioLogin(String usuario, String password);
}
