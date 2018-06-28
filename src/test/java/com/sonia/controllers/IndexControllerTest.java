package com.sonia.controllers;

import static org.junit.Assert.assertTrue;

import org.junit.*;

public class IndexControllerTest {

	IndexController indexController = new IndexController();
	
	@Test
	public void when_go_to_index_is_called_then_it_should_return_index() {
		String result = indexController.goToIndex();
		assertTrue(result.equals("index"));
	}
	
}
