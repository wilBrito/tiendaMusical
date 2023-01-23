package com.wilProject.tiendaMusicalServices.service;

import com.paypal.orders.Order;
import com.wilProject.tiendaMusicalEntities.entities.Factura;
import com.wilProject.tiendaMusicalEntities.entities.Persona;

public interface FacturaService {

	Factura guardarFactura(Factura factura, Order order, Persona persona);
}
