package com.sonia.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sonia.entities.Order;
import com.sonia.entities.User;
import com.sonia.pageLogics.EditUserLogic;
import com.sonia.pageLogics.ViewHistoryLogic;

public class UserCenterControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@Mock
	RedirectAttributes redir;
	@Mock
	ViewHistoryLogic viewHistoryLogic;
	@Mock
	EditUserLogic editUserLogic;
	@Mock
	List<Order> mockOrderList;
	@Mock
	Order mockOrder;
	@Mock
	User mockUser;
	@Mock
	ModelMap model;
	@InjectMocks
	UserCenterController userCenterController = new UserCenterController();

	int orderId = 101;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void goToUserCenter_should_return_userCenter_page() {
		String result = userCenterController.goToUserCenter(request, session);
		
		assertEquals("userCenter", result);
	}

	@Test
	public void viewPersonalOrderHistory_should_return_view_history_page() {
		when(viewHistoryLogic.personalOrderHistory(session)).thenReturn(mockOrderList);
		
		String result = userCenterController.viewPersonalOrderHistory(request, session);
		
		verify(viewHistoryLogic).personalOrderHistory(session);
		verify(request).setAttribute("orders", mockOrderList);
		assertEquals("viewHistory", result);
	}
	
	@Test
	public void viewOrderDetail_should_assign_order_to_the_request_then_return_view_order_detail_page() {
		when(viewHistoryLogic.viewOrderDetail(orderId, session)).thenReturn(mockOrder);
		
		String result = userCenterController.viewOrderDetail(orderId, request, session);
	
		verify(request).setAttribute("order", mockOrder);
		assertEquals("viewOrderDetail", result);
	}
	
	@Test
	public void editUserInfo_should_return_edit_user_info_page() {
		when(session.getAttribute("user")).thenReturn(mockUser);
		
		String result = userCenterController.editUserInfo(model, request, session);
		
		verify(model).addAttribute("editUser", mockUser);
		assertEquals("updateUserInfo", result);
		
	}
	
	@Test
	public void submitEditUserInfo_should_return_to_the_original_page_when_update_successed() {
		when(model.get("editUser")).thenReturn(mockUser);
		when(editUserLogic.editUser(request, session, mockUser)).thenReturn(true);
		
		String result = userCenterController.submitEditUserInfo(mockUser, request, session, redir);
		
		verify(editUserLogic).editUser(request, session, mockUser);
		verify(redir).addFlashAttribute("message", "Your information was successfully updated.");
		assertEquals("redirect:userCenter", result);
	}
	
	@Test
	public void submitEditUserInfo_should_return_to_the_update_user_page_when_the_update_failed() {
		when(model.get("editUser")).thenReturn(mockUser);
		when(editUserLogic.editUser(request, session, mockUser)).thenReturn(false);
		when(request.getHeader("Referer")).thenReturn("updateUserInfo");
		
		String result = userCenterController.submitEditUserInfo(mockUser, request, session, redir);
		
		verify(editUserLogic).editUser(request, session, mockUser);
		verify(redir).addFlashAttribute("message", "Your original passowrd is not correct, please try again.");
		assertEquals("redirect:updateUserInfo", result);
	}
}
