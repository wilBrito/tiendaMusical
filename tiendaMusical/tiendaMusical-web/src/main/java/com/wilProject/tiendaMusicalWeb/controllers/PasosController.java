package com.wilProject.tiendaMusicalWeb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wilProject.tiendaMusicalWeb.session.SessionBean;
import com.wilProject.tiendaMusicalWeb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class PasosController {
	
	private static final Logger LOGGER = LogManager.getLogger(PasosController.class);
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;

	@PostConstruct
	public void init() {
		LOGGER.info("Ingresando a PasosController...");
	}
	
	
	public void cambiarPaso(String url, int paso) {
		try {
			this.sessionBean.setPaso(paso);
			CommonUtils.redireccionar(url);
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"UPS!", "Hubo un problema al tratar de ingresar al siguiente paso de la compra, por favor intentarlo más tarde.");
			e.printStackTrace();
		}
	}


	
	
	
	
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}


	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	
	
}
