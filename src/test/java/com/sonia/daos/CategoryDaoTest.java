package com.sonia.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.entities.Category;

public class CategoryDaoTest {
	
	@Mock
	EntityManagerFactory emf;
	@Mock
	EntityManager em;
	@Mock
	TypedQuery<Category> mockTypedQuery;
	@Mock
	List<Category> mockCategoryQueryReturnList;
	@Mock
	Category mockCategory;
	@InjectMocks
	CategoryDao categoryDao = new CategoryDao();
	
	String categoryName = "clothing";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getCategoryByName_should_create_a_typed_query_and_return_a_category() {
		mocksSetup();
		when(mockCategoryQueryReturnList.size()).thenReturn(1);
		when(mockCategoryQueryReturnList.get(0)).thenReturn(mockCategory);
		
		Category result = categoryDao.getCategoryByName(categoryName);
		assertEquals(mockCategory, result);
	}

	@Test
	public void getCategoryByName_should_return_null_if_no_related_category() {
		mocksSetup();
		when(mockCategoryQueryReturnList.size()).thenReturn(0);
		
		Category result = categoryDao.getCategoryByName(categoryName);
		assertNull(result);
	}
	
	private void mocksSetup() {
		when(emf.createEntityManager()).thenReturn(em);
		when(em.createQuery("SELECT t FROM Category t WHERE category_name = \'clothing\'", Category.class)).thenReturn(mockTypedQuery);
		when(mockTypedQuery.getResultList()).thenReturn(mockCategoryQueryReturnList);
		
	}
}
