package com.wilProject.tiendaMusicalServices.service;

import java.util.List;

import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalEntities.entities.Carrito;
import com.wilProject.tiendaMusicalEntities.entities.CarritoAlbum;
import com.wilProject.tiendaMusicalEntities.entities.Factura;

public interface CarritoService {

	CarritoAlbum guardarAlbumsCarrito(ArtistaAlbumDto artistaAlbumDto, Carrito carrito, int cantidadAlbumSeleccionada);
	
	float calacularTotal(Carrito carrito);
	
	void eliminarAlbumCarrito(CarritoAlbum carritoAlbum);
	
	float actualizarCantidadAlbumCarrito(CarritoAlbum carritoAlbum, Carrito carrito);
	
	boolean actualizarCarritoAlbum(List<CarritoAlbum> carritoAlbums, Factura factura);
}
