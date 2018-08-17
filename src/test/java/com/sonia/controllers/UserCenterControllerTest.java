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

import com.sonia.entities.Order;
import com.sonia.entityHandlers.OrderHandler;
import com.sonia.pageLogics.ViewHistoryLogic;

public class UserCenterControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@Mock
	ViewHistoryLogic viewHistoryLogic;
	@Mock
	OrderHandler orderHandler;
	@Mock
	List<Order> mockOrderList;
	@Mock
	Order mockOrder;
	@InjectMocks
	UserCenterController userCenterController = new UserCenterController();

	int orderId = 101;
	double totalAmount = 122350.0;
	
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
		when(orderHandler.calculateOrderTotalCost(mockOrder)).thenReturn(totalAmount);
		
		String result = userCenterController.viewOrderDetail(orderId, request, session);
	
		verify(request).setAttribute("totalAmount", totalAmount);
		verify(request).setAttribute("order", mockOrder);
		assertEquals("viewOrderDetail", result);
	}
}
