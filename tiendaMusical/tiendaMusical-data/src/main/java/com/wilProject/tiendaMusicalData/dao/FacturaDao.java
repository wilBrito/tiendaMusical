package com.wilProject.tiendaMusicalData.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.wilProject.tiendaMusicalEntities.entities.Factura;

public interface FacturaDao extends PagingAndSortingRepository<Factura, Long> {

}
