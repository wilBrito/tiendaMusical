/**
 * 
 */
package com.wilProject.tiendaMusicalWeb.controllers;




import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.wilProject.tiendaMusicalEntities.entities.Persona;
import com.wilProject.tiendaMusicalServices.service.LoginService;
import com.wilProject.tiendaMusicalWeb.utils.CommonUtils;


/**
 * @author wilson
 * Controlador que se encarga del login.xhtml
 *
 */

@ManagedBean
@ViewScoped
public class LoginController{

	private String usuario;
	private String password;
	
	@ManagedProperty("#{loginServiceImpl}")
	private LoginService lSer;
	
	@PostConstruct
	public void publi() {
		System.out.println("Inicializando login");
	}
	
	
	public void entrar() {
		System.out.println(this.usuario + " " + this.password);
		
		Persona personaConsultada = this.lSer.consultarUsuarioLogin(this.usuario, this.password);
		
		if(personaConsultada != null)
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO,"EXITOSO","Bienvenido");
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
	
	
	
}
