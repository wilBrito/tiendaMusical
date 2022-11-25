package com.wilProject.tiendaMusicalWeb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalServices.service.HomeService;

@ManagedBean
@ViewScoped
public class HomeController {

	private String filtro;
	
	private List<ArtistaAlbumDto> artistaAlbumDto;
	
	@ManagedProperty("#{homeServiceImpl}")
	private HomeService homeServiceImpl;
	
	@PostConstruct
	public void init() {
		System.out.println("Inicializando Home");
	}
	
	
	public void consultarAlbumArtistaPorFiltro() {
		this.artistaAlbumDto = homeServiceImpl.consultarAlbumsFiltro(filtro);
		
		if(this.artistaAlbumDto != null) {
			this.artistaAlbumDto.forEach(artistaAlbumDto -> {
				System.out.println("Artista " + artistaAlbumDto.getArtista().getNombre());
			});
		}
	}
	
	
	

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<ArtistaAlbumDto> getArtistaAlbumDto() {
		return artistaAlbumDto;
	}

	public void setArtistaAlbumDto(List<ArtistaAlbumDto> artistaAlbumDto) {
		this.artistaAlbumDto = artistaAlbumDto;
	}

	public HomeService getHomeServiceImpl() {
		return homeServiceImpl;
	}

	public void setHomeServiceImpl(HomeService homeServiceImpl) {
		this.homeServiceImpl = homeServiceImpl;
	}
	
	
	
	
	
}


