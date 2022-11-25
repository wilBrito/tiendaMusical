package com.wilProject.tiendaMusicalData.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto;
import com.wilProject.tiendaMusicalEntities.entities.Artista;

public interface ArtistaDao extends PagingAndSortingRepository<Artista, Long> {
	
	@Query("SELECT new com.wilProject.tiendaMusicalEntities.dto.ArtistaAlbumDto(a, ar) "
			+ "FROM Album a "
			+ "INNER JOIN a.artista ar "
			+ "INNER JOIN ar.subGenero sg "
			+ "INNER JOIN sg.genero g "
			+ "WHERE ar.nombre LIKE %:filtro% "
			+ "OR g.descripcion LIKE %:filtro% "
			+ "OR sg.descripcion LIKE %:filtro% "
			+ "OR a.nombre LIKE %:filtro% "
			+ "ORDER BY ar.nombre")
	public List<ArtistaAlbumDto> consultarArtistasAlbumsPorFiltro(@Param("filtro") String filtro);

}
