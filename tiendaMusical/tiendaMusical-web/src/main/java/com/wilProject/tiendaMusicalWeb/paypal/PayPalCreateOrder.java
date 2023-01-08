package com.wilProject.tiendaMusicalWeb.paypal;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.paypal.http.HttpResponse;
import com.paypal.orders.AddressPortable;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Item;
import com.paypal.orders.Money;
import com.paypal.orders.Name;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.Payer;
import com.paypal.orders.PurchaseUnitRequest;
import com.paypal.orders.ShippingDetail;
import com.wilProject.tiendaMusicalEntities.entities.CarritoAlbum;
import com.wilProject.tiendaMusicalWeb.session.SessionBean;

public class PayPalCreateOrder extends PayPalClient {
	
	private static final Logger LOGGER = LogManager.getLogger(PayPalCreateOrder.class);
	
	private OrderRequest orderRequest;
	
	
	
	public HttpResponse<Order> crearOrden(SessionBean sessionBean) throws IOException {
			LOGGER.info("Ingresando a la funcion de crearOrden...");
			OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest();
			ordersCreateRequest.prefer("return=representation");
			ordersCreateRequest.requestBody(this.generarCuerpoOrden(sessionBean));
			
			HttpResponse<Order> response = client().execute(ordersCreateRequest);
			
			sessionBean.setOrder(response);
			
			return response;
	}
	
		
	private OrderRequest generarCuerpoOrden(SessionBean sessionBean) {
		this.orderRequest = new OrderRequest();
		
		//Aprobacion de compra
		Payer payer = new Payer();
		this.orderRequest.checkoutPaymentIntent("CAPTURE");
		
		//Nombre de la empresa y la direccion, landingpage
		ApplicationContext applicationContext = new ApplicationContext().brandName("WilProject")
				.landingPage("BILLING").shippingPreference("SET_PROVIDED_ADDRESS");
		
		this.orderRequest.applicationContext(applicationContext);
		
		//Datos personales del comprador
		String nombre = sessionBean.getPersona().getNombre();
		String primerApellido = sessionBean.getPersona().getPrimerApellido();
		String segundoApellido = sessionBean.getPersona().getSegundoApellido();
		
		//Lista de unidades de compra
		List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();		
		List<Item> items = new ArrayList<Item>();
		
		//Se obtine los productos seleccionados
		List<CarritoAlbum> carritoAlbums = sessionBean.getPersona().getCarrito().getCarritoAlbum();
		
		//Variables que indicaran los impuestos de la compra
		double impuestoPorProducto = 10.00;
		double impuestoSumaTotalProducto = 0.0;
		
		//Se obtine y se itera cada producto del carrito y se agrega al objeto item de paypals
		for (CarritoAlbum carritoAlbum : carritoAlbums) {
			Item item = new Item();
			
			item.name(carritoAlbum.getAlbum().getNombre());
			
			String descripcionCorta = carritoAlbum.getAlbum().getDescripcion().substring(0, 50);
			
			item.description(descripcionCorta);
			item.unitAmount(new Money().currencyCode("EUR").value(String.valueOf(carritoAlbum.getAlbum().getValor())));
			item.tax(new Money().currencyCode("EUR").value(String.valueOf(impuestoPorProducto)));
			item.quantity(String.valueOf(carritoAlbum.getCantidad()));
			item.category("PHYSICAL_GOODS");
			item.sku("sku1");
			
			items.add(item);
			
			impuestoSumaTotalProducto += (impuestoPorProducto * carritoAlbum.getCantidad());
		}
		
		//Se configura el detalle del envio de los productos
		ShippingDetail shippingDetail = new ShippingDetail();
		shippingDetail.name(new Name().fullName(nombre + " " + primerApellido + " " + segundoApellido));
		shippingDetail.addressPortable(new AddressPortable().addressLine1("MI CALLE 1")
															.addressLine2("MI CASA")
															.adminArea1("ESPAÑA")
															.adminArea2("MADRID")
															.postalCode("01011")
															.countryCode("ES"));
		
		//Se aplica un formato a la cantidad del total de compra de los productos
		
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		System.err.println(decimalFormat.format(sessionBean.getTotalCompra()));
		double totalConDecimales = Double.parseDouble(decimalFormat.format(sessionBean.getTotalCompra()).replace(",", "."));
		System.err.println(totalConDecimales);
		
		double envio = 20.00;
		double handling = 10.00;
		double descuentoEnvio = 20.00;
		
		double totalCompraConImpuesto = totalConDecimales + impuestoSumaTotalProducto + handling + (envio - descuentoEnvio);

		//Se agrega la informacion calculada con los datos de la compra
		PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest();
		purchaseUnitRequest.items(items);
		purchaseUnitRequest.shippingDetail(shippingDetail);
		
		purchaseUnitRequest.amountWithBreakdown(new AmountWithBreakdown()
							.currencyCode("EUR").value(String.valueOf(totalCompraConImpuesto))
							.amountBreakdown(new AmountBreakdown()
							.itemTotal(new Money().currencyCode("EUR").value(String.valueOf(totalConDecimales)))
							.shipping(new Money().currencyCode("EUR").value(String.valueOf(envio)))
							.handling(new Money().currencyCode("EUR").value(String.valueOf(handling)))
							.taxTotal(new Money().currencyCode("EUR").value(String.valueOf(impuestoSumaTotalProducto)))
							.shippingDiscount(new Money().currencyCode("EUR").value(String.valueOf(descuentoEnvio)))));
		
		//Se agrega la unidad de compra a la lista y a la orden final de compra
		purchaseUnitRequests.add(purchaseUnitRequest);
		this.orderRequest.purchaseUnits(purchaseUnitRequests);
		
		payer.addressPortable(purchaseUnitRequest.shippingDetail().addressPortable());
		this.orderRequest.payer(payer);
		Gson gson = new Gson();
		LOGGER.info(gson.toJson(purchaseUnitRequests));
		
		return this.orderRequest;
	}
	
	

}
