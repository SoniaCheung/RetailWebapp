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

import com.sonia.daos.UserDao;
import com.sonia.entities.User;

public class LoginControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession httpSession;
	@Mock
	UserDao userDao;
	@Mock
	User user;
	@InjectMocks
	LoginController loginController = new LoginController();
	
	String username = "username";
	String password = "password";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void goToLoginPage_should_return_userLogin_page() {
		String result = loginController.goToLoginPage(request, httpSession);
		
		assertEquals("userLogin", result);
	}
	
	@Test
	public void verifyLoginInfo_should_call_loginLogic_to_verify_user_inputs_and_return_user_center_page_if_successfully_login() {
		when(request.getParameter("username")).thenReturn(username);
		when(request.getParameter("password")).thenReturn(password);
		when(userDao.verifyLoginInfo(username, password)).thenReturn(user);
		
		String result = loginController.loginUser(request, httpSession);
		
		verify(userDao).verifyLoginInfo(username, password);
		verify(httpSession).setAttribute("user", user);
		assertEquals("redirect:userCenter", result);
	};

	@Test
	public void verifyLoginInfo_should_call_loginLogic_to_verify_user_inputs_and_return_login_page_with_error_message_if_login_failed() {
		when(request.getParameter("username")).thenReturn(username);
		when(request.getParameter("password")).thenReturn(password);
		when(userDao.verifyLoginInfo(username, password)).thenReturn(null);
		
		String result = loginController.loginUser(request, httpSession);
		
		verify(userDao).verifyLoginInfo(username, password);
		verify(request).setAttribute("loginError", "Invalid inputs. Please try again.");
		assertEquals("userLogin", result);
	};
	
	@Test
	public void logout_should_clean_the_use_information_in_the_session_then_return_to_original_page() {
		when(request.getHeader("Referer")).thenReturn("productDetail?id=1");
		
		String result = loginController.logOut(request, httpSession);
		
		verify(httpSession).removeAttribute("user");
		assertEquals("redirect:productDetail?id=1", result);
	}
}
