package com.wilProject.tiendaMusicalWeb.session;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalEntities.entities.Persona;

@ManagedBean
@SessionScoped
public class SessionBean {
	
	private Persona persona;
	
	private ArtistaAlbumDto artistaAlbumDto;
	
	private float totalCompra;
	
	private HttpResponse<Order> order;
	
	private int paso;
	
	@PostConstruct
	public void init() {
		System.out.println("Creando sesion...");
		
	}
	
	
	

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}




	public ArtistaAlbumDto getArtistaAlbumDto() {
		return artistaAlbumDto;
	}




	public void setArtistaAlbumDto(ArtistaAlbumDto artistaAlbumDto) {
		this.artistaAlbumDto = artistaAlbumDto;
	}




	public float getTotalCompra() {
		return totalCompra;
	}




	public void setTotalCompra(float totalCompra) {
		this.totalCompra = totalCompra;
	}




	public void setOrder(HttpResponse<Order> response) {
		this.order = response;
		
	}

	public HttpResponse<Order> getOrder() {
		return order;
	}




	public int getPaso() {
		return paso;
	}




	public void setPaso(int paso) {
		this.paso = paso;
	}
	
	
	
	
	
	
	
}
