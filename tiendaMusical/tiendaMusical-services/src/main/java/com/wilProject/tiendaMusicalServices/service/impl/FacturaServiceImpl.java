package com.wilProject.tiendaMusicalServices.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.orders.Order;
import com.wilProject.tiendaMusicalData.dao.FacturaDao;
import com.wilProject.tiendaMusicalEntities.entities.Factura;
import com.wilProject.tiendaMusicalEntities.entities.Persona;
import com.wilProject.tiendaMusicalServices.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {
	
	@Autowired
	private FacturaDao facturaDaoImpl;

	@Override
	public Factura guardarFactura(Factura factura, Order order, Persona persona) {
		
		double envio = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shipping().value());
		double envioDescuento = Double.parseDouble((order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shippingDiscount().value()));
		double handling = Double.parseDouble((order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().handling().value()));
		double total = Double.parseDouble((order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().itemTotal().value()));
		double impuestoTotal = Double.parseDouble((order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().taxTotal().value()));
		
		factura.setOrderId(order.id());
		factura.setFechaCreacion(LocalDateTime.now());
		factura.setEstatus(true);
		factura.setPais(order.payer().addressPortable().adminArea1());
		factura.setCiudad(order.payer().addressPortable().adminArea2());
		factura.setCodigoPostal(order.payer().addressPortable().postalCode());
		factura.setDireccion(order.payer().addressPortable().addressLine1() + ", " + order.payer().addressPortable().addressLine2());
		factura.setDivisa(order.purchaseUnits().get(0).amountWithBreakdown().currencyCode());
		factura.setEnvio(envio);	
		factura.setEnvioDescuento(envioDescuento);
		factura.setHandling(handling);
		factura.setTotal(total);
		factura.setImpuestoTotal(impuestoTotal);
		factura.setPersona(persona);
		
		return this.facturaDaoImpl.save(factura);

	}

}
