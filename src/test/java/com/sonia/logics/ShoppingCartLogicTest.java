package com.sonia.logics;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	@Mock
	Set<Product> mockProductKeys;
	@Mock
	Iterator<Product> mockProductKeyItertor;
	
	@Mock
	Set<Map.Entry<Product, Integer>> mockProductEntrySet;
	@Mock
	Map.Entry<Product, Integer> mockEntry;
	@Mock
	Iterator<Map.Entry<Product, Integer>> mockEntrySetIterator;
	
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
		verify(mockShoppingCart).updateTotalAmount();
		verify(session).setAttribute("shoppingCart", mockShoppingCart);
	}
	
	@Test
	public void addToShoppingCart_should_add_an_item_to_shoppingCart_productMap_if_product_is_not_exists_in_map() {
		when(session.getAttribute("shoppingCart")).thenReturn(mockShoppingCart);
		when(productDao.getEntity(productId)).thenReturn(mockProduct);
		
		when(mockShoppingCart.getProductMap()).thenReturn(mockProductMap);
		when(mockProductMap.keySet()).thenReturn(mockProductKeys);
		when(mockProductKeys.iterator()).thenReturn(mockProductKeyItertor);
		when(mockProductKeyItertor.hasNext()).thenReturn(false);
		
		shoppingCartLogic.addToShoppingCart(productId, quantity, request, session);
		
		verify(mockProductMap).put(mockProduct, quantity);
		verify(mockShoppingCart).updateTotalAmount();
	}

	@Test
	public void addToShoppingCart_should_update_item_quantity_if_product_exists_in_map() {
		when(session.getAttribute("shoppingCart")).thenReturn(mockShoppingCart);
		when(productDao.getEntity(productId)).thenReturn(mockProduct);
		
		when(mockShoppingCart.getProductMap()).thenReturn(mockProductMap);
		when(mockProductMap.keySet()).thenReturn(mockProductKeys);
		when(mockProductKeys.iterator()).thenReturn(mockProductKeyItertor);
		when(mockProductKeyItertor.hasNext()).thenReturn(true).thenReturn(false);
		when(mockProductKeyItertor.next()).thenReturn(mockProduct);
		
		when(mockProduct.getId()).thenReturn(productId);
		
		when(mockProductMap.entrySet()).thenReturn(mockProductEntrySet);
		when(mockProductEntrySet.iterator()).thenReturn(mockEntrySetIterator);
		when(mockEntrySetIterator.hasNext()).thenReturn(true).thenReturn(false);
		when(mockEntrySetIterator.next()).thenReturn(mockEntry);
		when(mockEntry.getKey()).thenReturn(mockProduct);
		when(mockEntry.getValue()).thenReturn(2);
		
		shoppingCartLogic.addToShoppingCart(productId, quantity, request, session);
		
		verify(mockEntry).setValue(5);
		verify(mockShoppingCart).updateTotalAmount();
	}
}
