package com.wilProject.tiendaMusicalServices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilProject.tiendaMusicalData.dao.ArtistaDao;
import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalServices.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private ArtistaDao arDao;
	
	@Override
	public List<ArtistaAlbumDto> consultarAlbumsFiltro(String filtro) {
		return this.arDao.consultarArtistasAlbumsPorFiltro(filtro);
	}

}
