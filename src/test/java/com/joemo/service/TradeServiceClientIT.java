package com.joemo.service;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.junit.Assert;
import org.junit.Test;

import trade.schema.joemo.com.Status;
import trade.schema.joemo.com.Trade;
import tradeservice.schema.joemo.com.TradeService;

public class TradeServiceClientIT {

	private static final class MyService extends javax.xml.ws.Service {
		private MyService(URL wsdlDocumentLocation, QName serviceName) {
			super(wsdlDocumentLocation, serviceName);
		}
	}

	@Test
	public void testDummy() throws MalformedURLException {
		QName serviceName = new QName("http://service.joemo.com/", "TradeServiceImplService");
		MyService myService = new MyService(new URL("http://localhost:8080/ws-example/ws-example?wsdl"), serviceName);

		QName portName = new QName("http://service.joemo.com/", "TradeServiceImplPort");
		TradeService port = myService.getPort(portName, TradeService.class);
		Trade trade = new Trade();
		trade.setQuantity(BigInteger.valueOf(1));
		trade.setSecurity("TestSecurity");

		Status book = port.book(trade);

		assertEquals("12345", book.getId());
		assertEquals("ok", book.getMessage());
	}

}
