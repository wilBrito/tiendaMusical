package com.wilProject.tiendaMusicalWeb.controllers;


import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.wilProject.tiendaMusicalEntities.entities.CarritoAlbum;
import com.wilProject.tiendaMusicalEntities.entities.Factura;
import com.wilProject.tiendaMusicalEntities.entities.Persona;
import com.wilProject.tiendaMusicalServices.client.ReportesServiceClient;
import com.wilProject.tiendaMusicalServices.service.CarritoService;
import com.wilProject.tiendaMusicalServices.service.FacturaService;
import com.wilProject.tiendaMusicalWeb.session.SessionBean;
import com.wilProject.tiendaMusicalWeb.utils.CommonUtils;

@ManagedBean
@ViewScoped
public class PagoController {

	private static final Logger LOGGER = LogManager.getLogger(PagoController.class);
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	@ManagedProperty("#{facturaServiceImpl}")
	private FacturaService facturaServiceImpl;
	
	@ManagedProperty("#{carritoServiceImpl}")
	private CarritoService carritoServiceImpl;
	
	
	@ManagedProperty("#{reportesServiceClient}")
	private ReportesServiceClient reportesServiceClient;
	
	
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializa el proceso de pago...");
	}
	
	public void guardarFactura() {
		LOGGER.info("Guardando factura...");
		
		HttpResponse<Order> order = this.sessionBean.getOrder();		
		Persona persona = this.sessionBean.getPersona();
		Factura factura = new Factura();
		
		Factura facturaGenerada = this.facturaServiceImpl.guardarFactura(factura, order.result(), persona);
		
		if(facturaGenerada != null) {
			
			boolean productosCarritoActualizados = 
					this.carritoServiceImpl.actualizarCarritoAlbum(persona.getCarrito().getCarritoAlbum(), facturaGenerada);
			
			if(productosCarritoActualizados) {
				
				String cliente = persona.getNombre()  + persona.getPrimerApellido()  + persona.getSegundoApellido();
				
				Response response = this.reportesServiceClient.generarReporte(order.result().id(), persona.getEmail(), cliente);
				
				LOGGER.info("Response: " + response.getStatus());
				
				this.sessionBean.getPersona().getCarrito().setCarritoAlbum(new ArrayList<CarritoAlbum>());
				this.cambiarPaso("/pages/cliente/confirmacion.xhtml", 2);
			}
			
		}
			
		else
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "ERROR!", "No se generó la factura");
	}
	
	
	public void cambiarPaso(String url, int paso) {
		try {
			this.sessionBean.setPaso(paso);
			CommonUtils.redireccionar(url);
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"UPS!", "Hubo un problema al tratar de ingresar al siguiente paso de la compra, por favor intentarlo más tarde.");
			e.printStackTrace();
		}
	}
	
	
	
	

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public FacturaService getFacturaServiceImpl() {
		return facturaServiceImpl;
	}

	public void setFacturaServiceImpl(FacturaService facturaServiceImpl) {
		this.facturaServiceImpl = facturaServiceImpl;
	}

	public CarritoService getCarritoServiceImpl() {
		return carritoServiceImpl;
	}

	public void setCarritoServiceImpl(CarritoService carritoServiceImpl) {
		this.carritoServiceImpl = carritoServiceImpl;
	}

	public ReportesServiceClient getReportesServiceClient() {
		return reportesServiceClient;
	}

	public void setReportesServiceClient(ReportesServiceClient reportesServiceClient) {
		this.reportesServiceClient = reportesServiceClient;
	}
	
	
	
	
}
