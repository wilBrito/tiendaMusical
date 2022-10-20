package com.wilProject.tiendaMusicalData.dao.impl;

import com.wilProject.tiendaMusicalData.common.CommonDao;
import com.wilProject.tiendaMusicalData.dao.PersonaDao;
import com.wilProject.tiendaMusicalEntities.entities.Persona;

public class PersonaDaoImpl extends CommonDao<Persona, PersonaDao>{

	public Persona findByUsuarioAndPassword(String usuario, String password) {
		return this.repository.findByUsuarioAndPassword(usuario, password);
	}
}
