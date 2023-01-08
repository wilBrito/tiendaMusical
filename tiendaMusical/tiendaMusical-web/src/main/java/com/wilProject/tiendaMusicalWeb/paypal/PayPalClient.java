package com.wilProject.tiendaMusicalWeb.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;


public class PayPalClient {
	
	private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
			"AUdpMK7l9mjA4g2ur-oce8X0mGadSIf5R1A5EBUo6TkfAbhr6HLaKinJv2fYLqg23r0zRxT-bW6eHKbv",
			"EKEF9hx3a9Ab-hnrY6l3kGttkV46tH_lSlFmKuoyeqYXM9eJTqF_9lG8cmAmqMcCW_H5kVWw3HQ6QQEI");
	
	PayPalHttpClient client = new PayPalHttpClient(environment);
	
	
	public PayPalHttpClient client() {
		return this.client;
	}

}
