package com.wilProject.tiendaMusicalEntities.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Common {

	@Column(name = "fechaCreacion", nullable = false)
	private LocalDateTime fechaCreacion;
	
	@Column(name = "fechaModificacion")
	private LocalDateTime fechaActualizacion;
	
	@Column(name = "estatus")
	private boolean estatus;

	
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaModificacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	
}
