package com.wilProject.tiendaMusicalServices.service;

import java.util.List;

import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;

public interface HomeService {

	List<ArtistaAlbumDto> consultarAlbumsFiltro(String filtro);
}
