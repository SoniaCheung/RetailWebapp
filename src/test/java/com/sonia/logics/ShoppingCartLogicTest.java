package com.sonia.logics;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.daos.ProductDao;
import com.sonia.displayObjectFactories.ShoppingCartFactory;
import com.sonia.displayObjects.ShoppingCart;
import com.sonia.entities.Product;
import com.sonia.pageLogics.ShoppingCartLogic;

public class ShoppingCartLogicTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@Mock
	ShoppingCartFactory shoppingCartFactory;
	@Mock
	ShoppingCart mockShoppingCart;
	@Mock
	ProductDao productDao;
	@Mock
	Product mockProduct;
	@Mock
	Map<Product, Integer> mockProductMap;
	@InjectMocks
	ShoppingCartLogic shoppingCartLogic = new ShoppingCartLogic();
	
	int productId = 101;
	int quantity = 3;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addToShoppingCart_should_create_a_shoppingCart_if_session_shoppingCart_is_empty() {
		when(session.getAttribute("shoppingCart")).thenReturn(null);
		when(shoppingCartFactory.createShoppingCart()).thenReturn(mockShoppingCart);
		when(mockShoppingCart.getProductMap()).thenReturn(mockProductMap);
		when(productDao.getEntity(productId)).thenReturn(mockProduct);
		shoppingCartLogic.addToShoppingCart(productId, quantity, request, session);
		
		verify(shoppingCartFactory).createShoppingCart();
		verify(mockShoppingCart).setProductMap(any());
		verify(mockProductMap).put(mockProduct, quantity);
		verify(session).setAttribute("shoppingCart", mockShoppingCart);
	}
	
	@Test
	public void addToShoppingCart_should_add_an_item_to_shoppingCart_productMap() {
		when(session.getAttribute("shoppingCart")).thenReturn(mockShoppingCart);
		when(mockShoppingCart.getProductMap()).thenReturn(mockProductMap);
		when(productDao.getEntity(productId)).thenReturn(mockProduct);
		
		shoppingCartLogic.addToShoppingCart(productId, quantity, request, session);
		
		verify(mockShoppingCart).getProductMap();
		verify(mockProductMap).put(mockProduct, quantity);
	}

}
