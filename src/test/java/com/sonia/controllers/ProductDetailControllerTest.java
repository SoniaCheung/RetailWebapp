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

import com.sonia.daos.ProductDao;
import com.sonia.entities.Product;

public class ProductDetailControllerTest {

	@Mock
	HttpServletRequest httpServletRequest;
	@Mock
	HttpSession httpSession;
	@Mock
	ProductDao productDao;
	@Mock
	Product mockProduct;
	@InjectMocks
	ProductDetailController productDetailController = new ProductDetailController();
	
	int productId = 101;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void goToProductDetail_should_call_product_dao_to_get_product_and_return_productDetailPage() {
		when(productDao.getEntity(productId)).thenReturn(mockProduct);
		
		String result = productDetailController.goToProductDetail(productId, httpServletRequest, httpSession);
		
		verify(productDao).getEntity(productId);
		verify(httpServletRequest).setAttribute("product", mockProduct);
		assertEquals("productDetail", result);
	}

}
