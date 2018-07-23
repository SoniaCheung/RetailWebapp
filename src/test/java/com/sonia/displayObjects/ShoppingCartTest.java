package com.sonia.displayObjects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.entities.Product;

public class ShoppingCartTest {

	@Mock
	Map<Product, Integer> mockProductMap;
	@Mock
	Product mockProduct;
	@Mock
	Product mockProduct2;
	@InjectMocks
	ShoppingCart shoppingCart = new ShoppingCart();
	
	double price1 = 10.1;
	double price2 = 22.2;
	int quantity1 = 1;
	int quantity2 = 2;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void setProductMap_should_update_the_totalAmount_according_to_productMap() {
		when(mockProduct.getPrice()).thenReturn(price1);
		when(mockProduct2.getPrice()).thenReturn(price2);
		
		Map.Entry<Product, Integer> entry1 = new AbstractMap.SimpleEntry<Product, Integer>(mockProduct, quantity1);
		Map.Entry<Product, Integer> entry2 = new AbstractMap.SimpleEntry<Product, Integer>(mockProduct2, quantity2);
		Set<Map.Entry<Product, Integer>> mockSet= new HashSet<>();
		mockSet.add(entry1);
		mockSet.add(entry2);
		
		when(mockProductMap.entrySet()).thenReturn(mockSet);
		
		shoppingCart.setProductMap(mockProductMap);
		double result = shoppingCart.getTotalAmount();
		
		assertEquals(price1*quantity1 + price2*quantity2, result, 0.001);
		
	}

}
