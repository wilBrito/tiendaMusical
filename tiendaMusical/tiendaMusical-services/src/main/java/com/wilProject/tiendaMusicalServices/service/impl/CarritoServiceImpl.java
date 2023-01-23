package com.wilProject.tiendaMusicalServices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilProject.tiendaMusicalData.dao.CarritoAlbumDao;
import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalEntities.entities.Carrito;
import com.wilProject.tiendaMusicalEntities.entities.CarritoAlbum;
import com.wilProject.tiendaMusicalEntities.entities.Factura;
import com.wilProject.tiendaMusicalServices.service.CarritoService;

@Service
public class CarritoServiceImpl implements CarritoService {
	
	@Autowired
	private CarritoAlbumDao carritoAlbumDao;

	@Override
	public CarritoAlbum guardarAlbumsCarrito(ArtistaAlbumDto artistaAlbumDto, Carrito carrito, int cantidadAlbumSeleccionada) {
		
		CarritoAlbum carritoAlbum = new CarritoAlbum();
		carritoAlbum.setAlbum(artistaAlbumDto.getAlbum());
		carritoAlbum.setCarrito(carrito);
		carritoAlbum.setCantidad(cantidadAlbumSeleccionada);
		carritoAlbum.setEstatus("PENDIENTE");
		
		this.carritoAlbumDao.save(carritoAlbum);
		
		return carritoAlbum;

	}

	@Override
	public float calacularTotal(Carrito carrito) {
		float total = 0.0F;
		
		for (CarritoAlbum carritoAlbum : carrito.getCarritoAlbum()) {
			total += carritoAlbum.getAlbum().getValor() * carritoAlbum.getCantidad();
		}
		return total;
	}

	@Override
	public void eliminarAlbumCarrito(CarritoAlbum carritoAlbum) {
		this.carritoAlbumDao.delete(carritoAlbum);
		
	}

	@Override
	public float actualizarCantidadAlbumCarrito(CarritoAlbum carritoAlbum, Carrito carrito) {
		this.carritoAlbumDao.save(carritoAlbum);
		
		return this.calacularTotal(carrito);
		
	}

	@Override
	public boolean actualizarCarritoAlbum(List<CarritoAlbum> carritoAlbums, Factura factura) {
		
		boolean actualizados = false;
		
		for(CarritoAlbum carritoAlbum : carritoAlbums) {
			carritoAlbum.setEstatus("PAGADO");
			carritoAlbum.setFechaCompra(LocalDateTime.now());
			carritoAlbum.setFactura(factura);
		}
		
		Iterable<CarritoAlbum> carritosActualizados =  this.carritoAlbumDao.saveAll(carritoAlbums);
		
		carritosActualizados.forEach(ca -> {
			ca.getAlbum();
		});
		
		if(carritosActualizados != null) {
			actualizados = true;
		}
		
		return actualizados;
	}

}
