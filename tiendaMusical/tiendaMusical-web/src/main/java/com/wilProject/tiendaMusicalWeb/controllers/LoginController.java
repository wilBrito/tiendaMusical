/**
 * 
 */
package com.wilProject.tiendaMusicalWeb.controllers;




import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wilProject.tiendaMusicalEntities.entities.CarritoAlbum;
import com.wilProject.tiendaMusicalEntities.entities.Persona;
import com.wilProject.tiendaMusicalServices.service.LoginService;
import com.wilProject.tiendaMusicalWeb.session.SessionBean;
import com.wilProject.tiendaMusicalWeb.utils.CommonUtils;


/**
 * @author wilson
 * Controlador que se encarga del login.xhtml
 *
 */

@ManagedBean
@ViewScoped
public class LoginController{
	
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

	private String usuario;
	private String password;
	
	@ManagedProperty("#{loginServiceImpl}")
	private LoginService lSer;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@PostConstruct
	public void publi() {
		System.out.println("Inicializando login");
	}
	
	
	public void entrar() {
		
		
		System.out.println(this.usuario + " " + this.password);
		
		Persona personaConsultada = this.lSer.consultarUsuarioLogin(this.usuario, this.password);
		
		if(personaConsultada != null)
			try {
				List<CarritoAlbum> carritoAlbumFiltrados = personaConsultada.getCarrito().getCarritoAlbum().stream().filter(ca ->
					ca.getEstatus().equals("PENDIENTE")).collect(Collectors.toList());
				
				personaConsultada.getCarrito().setCarritoAlbum(carritoAlbumFiltrados);
				
				this.sessionBean.setPersona(personaConsultada);
				
				
				LOGGER.info("Albums de carritos filtrados");
				
				
				CommonUtils.redireccionar("/pages/commons/dashboard.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
				CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_FATAL,"ERROR!","Formato incorrecto");
			}
		else
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"UPS","El usuario y/o contraseña son incorrectos");
	}
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public LoginService getlSer() {
		return lSer;
	}


	public void setlSer(LoginService lSer) {
		this.lSer = lSer;
	}


	public SessionBean getSessionBean() {
		return sessionBean;
	}


	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	
	
	
}
