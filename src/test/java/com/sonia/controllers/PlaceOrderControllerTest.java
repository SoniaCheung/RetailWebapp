package com.sonia.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.pageLogics.PlaceOrderLogic;

public class PlaceOrderControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@Mock
	PlaceOrderLogic placeOrderLogic;
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
	
	@Test
	public void goToConfirmationPage_should_call_required_logic_methods_and_return_confirmationPage() {
		String result = controller.goToConfirmationPage(request, session);
		
		verify(placeOrderLogic).createOrderedProductByShoppingCart(session);
		verify(placeOrderLogic).createNewOrderByInfo(request, session);
		assertEquals("confirmationPage", result);
	}

}
