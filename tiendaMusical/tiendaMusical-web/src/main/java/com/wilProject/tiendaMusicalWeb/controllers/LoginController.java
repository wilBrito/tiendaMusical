/**
 * 
 */
package com.wilProject.tiendaMusicalWeb.controllers;




import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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
				this.sessionBean.setPersona(personaConsultada);
				
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
