package com.wilProject.tiendaMusicalWeb.session;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.wilProject.tiendaMusicalWeb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class SessionClosed {
	
	public void cerrarSession() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			CommonUtils.redireccionar("/login.xhtml");
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS", "Hubo un problema al volver al login");
			e.printStackTrace();
		}
	}

}
