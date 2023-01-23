package com.wilProject.tiendaMusicalWeb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wilProject.tiendaMusicalWeb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class PaginaController {
	
	private static final Logger LOGGER = LogManager.getLogger(PaginaController.class);
	
	

	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando controlador de paginaController");
	}
	
	public void redireccionarPagina(String pagina) {
		try {
			CommonUtils.redireccionar(pagina);
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un error al cambiar a la pantalla de " + pagina);
			e.printStackTrace();
		}
	}
}
