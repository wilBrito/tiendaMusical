package com.wilProject.tiendaMusicalWeb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wilProject.tiendaMusicalEntities.entities.CarritoAlbum;
import com.wilProject.tiendaMusicalServices.service.CarritoService;
import com.wilProject.tiendaMusicalWeb.session.SessionBean;

@ManagedBean
@ViewScoped
public class CarritoController {
	
	private static final Logger LOGGER = LogManager.getLogger(DetalleController.class);
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;

	@PostConstruct
	public void init() {
		this.calcularTotal();	
	}
	
	public void calcularTotal() {
		float total = this.carritoServiceImpl.calacularTotal(this.sessionBean.getPersona().getCarrito());
		
		this.sessionBean.setTotalCompra(total);
	}
	
	public void eliminarCarritoAlbum(CarritoAlbum carritoAlbum) {
		LOGGER.info("Eliminando album del carrito");
		
		this.carritoServiceImpl.eliminarAlbumCarrito(carritoAlbum);		
		this.sessionBean.getPersona().getCarrito().getCarritoAlbum().remove(carritoAlbum);
		this.calcularTotal();
	}
	
	public void actualizarCantidadCarrito(CarritoAlbum carritoAlbum) {
		float totalCompra = this.carritoServiceImpl.actualizarCantidadAlbumCarrito(carritoAlbum, this.sessionBean.getPersona().getCarrito());
		
		this.sessionBean.setTotalCompra(totalCompra);
	}
	
	
	

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}

	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
	}
	
	
}
