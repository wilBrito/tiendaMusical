package com.wilProject.tiendaMusicalWeb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalServices.service.HomeService;

@ManagedBean
@ViewScoped
public class HomeController {
	
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

	private String filtro;
	
	private List<ArtistaAlbumDto> artistaAlbumDto;
	
	@ManagedProperty("#{homeServiceImpl}")
	private HomeService homeServiceImpl;
	
	@PostConstruct
	public void init() {
		LOGGER.info("Info");
		LOGGER.warn("Warn");
		LOGGER.error("Error");
		LOGGER.fatal("Fatal");
	}
	
	
	public void consultarAlbumArtistaPorFiltro() {
		this.artistaAlbumDto = homeServiceImpl.consultarAlbumsFiltro(filtro);
		
		if(this.artistaAlbumDto != null) {
			this.artistaAlbumDto.forEach(artistaAlbumDto -> {
				LOGGER.info("Artista " + artistaAlbumDto.getArtista().getNombre());

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


