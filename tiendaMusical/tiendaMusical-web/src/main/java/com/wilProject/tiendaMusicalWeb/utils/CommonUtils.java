package com.wilProject.tiendaMusicalWeb.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

//Clase generada para crear clases generales para todo el proyecto

public class CommonUtils {
	
	
	//Método que permite mostrar un mensaje al usuario
	public static void mostrarMensaje(Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

}
