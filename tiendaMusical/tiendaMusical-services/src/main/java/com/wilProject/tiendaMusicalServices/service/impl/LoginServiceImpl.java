package com.wilProject.tiendaMusicalServices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilProject.tiendaMusicalData.dao.PersonaDao;
import com.wilProject.tiendaMusicalEntities.entities.Persona;
import com.wilProject.tiendaMusicalServices.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private PersonaDao pDao;
	
	@Override
	public Persona consultarUsuarioLogin(String usuario, String password) {
		return this.pDao.findByUsuarioAndPassword(usuario, password);
	}

}
