package com.sonia.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.entities.ProductImage;

public class ProductImageDaoTest {

	@Mock
	private EntityManagerFactory emf;
	@Mock
	private EntityManager em;
	@Mock
	private EntityTransaction et;
	@InjectMocks
	ProductImageDao productImageDao = new ProductImageDao();
	
	int productId = 101;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void productImageDao_should_return_a_list_of_productImages() {
		TypedQuery<ProductImage> productImageQuery = mock(TypedQuery.class);
		List<ProductImage> mockProductImageQueryReturnList = new ArrayList<>();
		
		setupEntityManagerMock();
		when(em.createQuery("SELECT t FROM ProductImage t WHERE product_id = 101", ProductImage.class)).thenReturn(productImageQuery);
		when(productImageQuery.getResultList()).thenReturn(mockProductImageQueryReturnList);
		
		List<ProductImage> result = productImageDao.listProductImagesByProductId(productId);
		assertSame(mockProductImageQueryReturnList, result);
	}
	
	private void setupEntityManagerMock(){
		when(emf.createEntityManager()).thenReturn(em);
		when(em.getTransaction()).thenReturn(et);
	}

}
