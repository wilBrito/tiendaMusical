package com.wilProject.tiendaMusicalEntities.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrito_album")
public class CarritoAlbum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCarritoAlbum")
	private Long idCarritoAlbum;
	
	@ManyToOne
	@JoinColumn(name = "idCarrito")
	private Carrito carrito;
	
	@ManyToOne
	@JoinColumn(name = "idAlbum")
	private Album album;
	
	@ManyToOne
	@JoinColumn(name = "idFactura")
	private Factura factura;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "estatus", length = 45)
	private String estatus;
	
	@Column(name = "fechaCompra")
	private LocalDateTime fechaCompra;
	
	
	
	
	

	public Long getIdCarritoAlbum() {
		return idCarritoAlbum;
	}

	public void setIdCarritoAlbum(Long idCarritoAlbum) {
		this.idCarritoAlbum = idCarritoAlbum;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	
	
	
	
}
