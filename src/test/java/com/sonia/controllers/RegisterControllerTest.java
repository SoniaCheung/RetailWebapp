package com.sonia.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.sonia.daos.UserDao;
import com.sonia.entities.User;
import com.sonia.factories.UserFactory;

public class RegisterControllerTest {

	@Mock
	User mockUser;
	@Mock
	BindingResult mockBindResult;
	@Mock
	ModelMap mockModel;
	@Mock
	UserFactory userFactory;
	@Mock
	UserDao userDao;
	@Mock
	HttpSession session;
	@InjectMocks
	RegisterController registerController = new RegisterController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void goToRegisterForm_should_return_register_page() {
		ModelAndView result = registerController.goToRegisterForm();
		
		verify(userFactory).createUser();
		assertNotNull(result);
	}
	
	@Test
	public void registerUser_should_return_user_center_page_if_successfully_registered() {
		when(userDao.addOrUpdateEntity(mockUser)).thenReturn(mockUser);
		String result = registerController.registerUser(mockUser, mockBindResult, mockModel, session);
		
		verify(userDao).addOrUpdateEntity(mockUser);
		assertEquals("userCenter", result);
	}

	@Test
	public void registerUser_should_return_error_page_if_registration_failed() {
		when(userDao.addOrUpdateEntity(mockUser)).thenReturn(null);
		String result = registerController.registerUser(mockUser, mockBindResult, mockModel, session);
		
		verify(userDao).addOrUpdateEntity(mockUser);
		assertEquals("errorPage", result);
	}
}
