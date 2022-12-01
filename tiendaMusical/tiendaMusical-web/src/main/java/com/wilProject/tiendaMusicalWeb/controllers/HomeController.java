package com.wilProject.tiendaMusicalWeb.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalServices.service.HomeService;
import com.wilProject.tiendaMusicalWeb.session.SessionBean;
import com.wilProject.tiendaMusicalWeb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class HomeController {
	
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

	private String filtro;
	
	private List<ArtistaAlbumDto> artistaAlbumDto;
	
	@ManagedProperty("#{homeServiceImpl}")
	private HomeService homeServiceImpl;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	
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
	
	public void verDetalleAlbum(ArtistaAlbumDto artistaAlbumDto) {
		this.sessionBean.setArtistaAlbumDto(artistaAlbumDto);
		
		try {
			CommonUtils.redireccionar("/pages/cliente/detalle.xhtml");
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!!", "Problema al cargar la página");
			e.printStackTrace();
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


	public SessionBean getSessionBean() {
		return sessionBean;
	}


	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	
	
	
	
}


