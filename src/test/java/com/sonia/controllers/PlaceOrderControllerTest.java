package com.sonia.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.entities.Order;
import com.sonia.entities.OrderedProduct;
import com.sonia.pageLogics.PlaceOrderLogic;

public class PlaceOrderControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@Mock
	List<OrderedProduct> orderedProducts;
	@Mock
	Order mockOrder;
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
		when(placeOrderLogic.createOrderedProductByShoppingCart(session)).thenReturn(orderedProducts);
		when(placeOrderLogic.createNewOrderByInfo(request, session, orderedProducts)).thenReturn(mockOrder);
		
		String result = controller.goToConfirmationPage(request, session);
		
		verify(placeOrderLogic).createOrderedProductByShoppingCart(session);
		verify(placeOrderLogic).createNewOrderByInfo(request, session, orderedProducts);
		verify(request).setAttribute("order", mockOrder);
		assertEquals("confirmationPage", result);
	}

}
