package com.wilProject.tiendaMusicalData.dao.impl;


import java.util.List;

import com.wilProject.tiendaMusicalData.common.CommonDao;
import com.wilProject.tiendaMusicalData.dao.ArtistaDao;
import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalEntities.entities.Artista;

public class ArtistaDaoImpl extends CommonDao<Artista, ArtistaDao>{

	public List<ArtistaAlbumDto> consultarArtistaAlbumPorFiltro(String filtro){
		return this.repository.consultarArtistasAlbumsPorFiltro(filtro);
	}
}
