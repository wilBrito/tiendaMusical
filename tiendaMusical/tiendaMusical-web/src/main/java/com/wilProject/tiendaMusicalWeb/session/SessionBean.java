package com.wilProject.tiendaMusicalWeb.session;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalEntities.entities.Persona;

@ManagedBean
@SessionScoped
public class SessionBean {
	
	private Persona persona;
	
	private ArtistaAlbumDto artistaAlbumDto;
	
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
	
	
	
	
	
}
