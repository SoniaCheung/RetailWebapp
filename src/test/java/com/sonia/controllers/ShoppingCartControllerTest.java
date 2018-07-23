package com.sonia.controllers;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ShoppingCartControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@InjectMocks
	ShoppingCartController shoppingCartController = new ShoppingCartController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void goToShoppingCart_should_return_shoppingCart_page() {
		String result = shoppingCartController.goToShoppingCart(request, session);
		
		assertEquals("shoppingCart", result);
	}

}
