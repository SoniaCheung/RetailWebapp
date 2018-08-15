package com.sonia.logics;

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
import com.sonia.pageLogics.EditUserLogic;

public class EditUserLogicTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@Mock
	User mockLoginedUser;
	@Mock
	User mockUser;
	@Mock
	UserDao userDao;
	@InjectMocks
	EditUserLogic editUserLogic = new EditUserLogic();
	
	String originalPw = "originalPw";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void editUser_should_update_the_user_in_DB_if_the_original_password_is_correct() {
		setupLoginedUser();
		when(request.getParameter("currentPassword")).thenReturn(originalPw);
		
		boolean result = editUserLogic.editUser(request, session, mockUser);
	
		verify(userDao).addOrUpdateEntity(mockUser);
		assertTrue(result);
	}

	@Test
	public void editUser_should_return_false_if_user_input_a_wrong_current_password() {
		setupLoginedUser();
		when(request.getParameter("currentPassword")).thenReturn("wrong");
		
		boolean result = editUserLogic.editUser(request, session, mockUser);
		
		assertFalse(result);
	}
	
	private void setupLoginedUser() {
		when(session.getAttribute("user")).thenReturn(mockLoginedUser);
		when(mockLoginedUser.getPassword()).thenReturn(originalPw);
		
	}
}
