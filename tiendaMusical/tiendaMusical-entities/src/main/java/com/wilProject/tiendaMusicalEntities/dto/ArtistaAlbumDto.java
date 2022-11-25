package com.wilProject.tiendaMusicalEntities.dto;

import com.wilProject.tiendaMusicalEntities.entities.Album;
import com.wilProject.tiendaMusicalEntities.entities.Artista;

public class ArtistaAlbumDto {

	private Artista artista;
	
	private Album album;

	public ArtistaAlbumDto() {
		super();
	}

	public ArtistaAlbumDto(Album album, Artista artista) {
		super();
		this.artista = artista;
		this.album = album;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
}
