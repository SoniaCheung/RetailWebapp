package com.sonia.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.displayObjects.IndexPageBasicProductDisplay;
import com.sonia.pageLogics.IndexPageLogic;

public class IndexControllerTest {
	@Mock
	HttpServletRequest httpServletRequest;
	@Mock
	HttpSession httpSession;
	@Mock
	IndexPageLogic indexPageLogic;
	@Mock
	List<IndexPageBasicProductDisplay> mockProductList;
	@Mock
	List<IndexPageBasicProductDisplay> mockProductList2;
	@Mock
	List<IndexPageBasicProductDisplay> mockProductList3;
	@InjectMocks
	IndexController indexController = new IndexController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_go_to_index_is_called_then_it_should_call_required_logic_methods_and_return_index() {
		when(indexPageLogic.getAllIndexPageBasicDisplayObjects()).thenReturn(mockProductList);
		
		String result = indexController.goToIndex(httpServletRequest, httpSession);
		verify(indexPageLogic).getAllIndexPageBasicDisplayObjects();
		verify(httpServletRequest).setAttribute("indexProductBasicDisaplays", mockProductList);
		assertTrue(result.equals("index"));
	}
	
	@Test
	public void when_go_to_category_page_is_called_then_it_should_get_the_keyword_then_call_required_logic_method_and_return_index() {
		String categoryRequestName = "clothing";
		when(indexPageLogic.getIndexPageBasicDisplayObjectsByCategory(categoryRequestName)).thenReturn(mockProductList2);
		
		String result = indexController.goToCategory(categoryRequestName, httpServletRequest, httpSession);
		verify(indexPageLogic).getIndexPageBasicDisplayObjectsByCategory(categoryRequestName);
		verify(httpServletRequest).setAttribute("indexProductBasicDisaplays", mockProductList2);
		assertEquals("index", result);
	}
	
	@Test
	public void when_findProducts_is_called_then_it_should_get_the_search_keyword_then_call_required_logic_method_and_return_index(){
		String searchKeyword = "searchKey";
		when(indexPageLogic.getIndexPageBasicDisplayObjectsBySearchKey(searchKeyword)).thenReturn(mockProductList3);
	
		String result = indexController.findProducts(searchKeyword, httpServletRequest, httpSession);
		verify(indexPageLogic).getIndexPageBasicDisplayObjectsBySearchKey(searchKeyword);
		verify(httpServletRequest).setAttribute("indexProductBasicDisaplays", mockProductList3);
		assertEquals("index", result);
	}
}
