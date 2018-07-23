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

import com.sonia.pageLogics.ShoppingCartLogic;

public class ShoppingCartControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@Mock
	ShoppingCartLogic  shoppingCartLogic;
	@InjectMocks
	ShoppingCartController shoppingCartController = new ShoppingCartController();

	int productId = 101;
	int quantity = 3;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void goToShoppingCart_should_return_shoppingCart_page() {
		String result = shoppingCartController.goToShoppingCart(request, session);
		
		assertEquals("shoppingCart", result);
	}

	@Test
	public void addProductToShoppingCart_should_back_to_previous_page() {
		when(request.getHeader("Referer")).thenReturn("productDetail?id=1");
		String result = shoppingCartController.addToShoppingCart(productId, quantity, request, session);
		
		verify(shoppingCartLogic).addToShoppingCart(productId, quantity, request, session);
		verify(request).getHeader("Referer");
		assertEquals("redirect:productDetail?id=1", result);
	}
}
