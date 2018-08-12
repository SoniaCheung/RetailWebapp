package com.sonia.entityHandlers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.entities.Coupon;
import com.sonia.entities.Order;
import com.sonia.entities.OrderedProduct;

public class OrderHandlerTest {

	@Mock
	Order mockOrder;
	@Mock
	List<OrderedProduct> mockOrderedProductList;
	@Mock
	Iterator<OrderedProduct> mockOrderedProductListIterator;
	@Mock
	OrderedProduct mockOrderedProduct;
	@Mock
	OrderedProduct mockOrderedProduct2;
	@Mock
	Coupon mockCoupon;
	@InjectMocks
	OrderHandler orderHandler = new OrderHandler();
	
	int quantity = 5;
	int quantity2 = 10;
	double price = 55.0;
	double price2 = 198.5;
	double discountRate = 0.88;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() {
		when(mockOrder.getOrderedProductList()).thenReturn(mockOrderedProductList);
		when(mockOrderedProductList.iterator()).thenReturn(mockOrderedProductListIterator);
		when(mockOrderedProductListIterator.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(mockOrderedProductListIterator.next()).thenReturn(mockOrderedProduct).thenReturn(mockOrderedProduct2);
	
		when(mockOrderedProduct.getPrice()).thenReturn(price);
		when(mockOrderedProduct2.getPrice()).thenReturn(price2);
		when(mockOrderedProduct.getQuantity()).thenReturn(quantity);
		when(mockOrderedProduct2.getQuantity()).thenReturn(quantity2);
		when(mockOrder.getCoupon()).thenReturn(mockCoupon);
		when(mockCoupon.getCouponRate()).thenReturn(discountRate);
		
		double result = orderHandler.calculateOrderTotalCost(mockOrder);
		
		assertEquals(1988.8, result, 0.01);
	}

}
