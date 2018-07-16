package com.sonia.controllers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.pageLogics.IndexPageLogic;

public class IndexControllerTest {
	@Mock
	HttpServletRequest httpServletRequest;
	@Mock
	IndexPageLogic indexPageLogic;
	@InjectMocks
	IndexController indexController = new IndexController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_go_to_index_is_called_then_it_should_call_required_logic_methods_and_return_index() {
		String result = indexController.goToIndex(httpServletRequest);
		verify(indexPageLogic).getAllIndexPageBasixDisplayObjects();
		assertTrue(result.equals("index"));
	}
	
}
