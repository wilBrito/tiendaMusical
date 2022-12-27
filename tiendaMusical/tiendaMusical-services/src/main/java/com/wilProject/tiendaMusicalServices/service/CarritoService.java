package com.wilProject.tiendaMusicalServices.service;

import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalEntities.entities.Carrito;
import com.wilProject.tiendaMusicalEntities.entities.CarritoAlbum;

public interface CarritoService {

	CarritoAlbum guardarAlbumsCarrito(ArtistaAlbumDto artistaAlbumDto, Carrito carrito, int cantidadAlbumSeleccionada);
	
	float calacularTotal(Carrito carrito);
	
	void eliminarAlbumCarrito(CarritoAlbum carritoAlbum);
	
	float actualizarCantidadAlbumCarrito(CarritoAlbum carritoAlbum, Carrito carrito);
	
}
