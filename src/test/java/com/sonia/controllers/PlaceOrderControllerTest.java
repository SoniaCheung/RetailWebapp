package com.sonia.controllers;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlaceOrderControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@InjectMocks
	PlaceOrderController controller = new PlaceOrderController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void goToDeliveryInfoFormPage_should_return_deliveryInfoForm() {
		String result = controller.goToDeliveryInfoFormPage(request, session);
	
		assertEquals("deliveryInfoForm", result);
	}

}
