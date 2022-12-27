package com.wilProject.tiendaMusicalWeb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalEntities.entities.CarritoAlbum;
import com.wilProject.tiendaMusicalServices.service.CarritoService;
import com.wilProject.tiendaMusicalWeb.session.SessionBean;

@ManagedBean
@ViewScoped
public class DetalleController {
	
	private static final Logger LOGGER = LogManager.getLogger(DetalleController.class);
	
	private int cantidadAlbumSeleccionada;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;
	
	@PostConstruct
	public void init() {
		this.cantidadAlbumSeleccionada = 1;
	}
	
	public void agregarAlbumCarrito(ArtistaAlbumDto artistaAlbumDto) {
		LOGGER.info("Agregamos album al carrito de compra..." + ", cantidad: " + this.cantidadAlbumSeleccionada);
		
		CarritoAlbum carritoAlbumAgregado = this.carritoServiceImpl.guardarAlbumsCarrito(artistaAlbumDto, this.sessionBean.getPersona().getCarrito(), this.cantidadAlbumSeleccionada);
	
		this.sessionBean.getPersona().getCarrito().getCarritoAlbum().add(carritoAlbumAgregado);
	}
	
	
	
	
	
	
	
	
	
	

	public int getCantidadAlbumSeleccionada() {
		return cantidadAlbumSeleccionada;
	}

	public void setCantidadAlbumSeleccionada(int cantidadAlbumSeleccionada) {
		this.cantidadAlbumSeleccionada = cantidadAlbumSeleccionada;
	}

	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}

	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	
	
	
	

}
