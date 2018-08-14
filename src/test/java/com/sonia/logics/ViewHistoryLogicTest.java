package com.sonia.logics;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.daos.OrderDao;
import com.sonia.entities.Order;
import com.sonia.entities.User;
import com.sonia.pageLogics.ViewHistoryLogic;

public class ViewHistoryLogicTest {

	@Mock
	HttpSession session;
	@Mock
	User mockUser;
	@Mock
	User mockUser2;
	@Mock
	Order order;
	@Mock
	List<Order> mockOrderList;
	@Mock
	OrderDao orderDao;
	@InjectMocks
	ViewHistoryLogic viewHistoryLogic = new ViewHistoryLogic();
	
	int orderId = 50;
	
	int mockUserId = 101;
	int orderUserId = 222;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void personalOrderHistory_should_return_all_order_from_the_seesion_user() {
		when(session.getAttribute("user")).thenReturn(mockUser);
		when(mockUser.getOrderList()).thenReturn(mockOrderList);
		
		List<Order> result = viewHistoryLogic.personalOrderHistory(session);
	
		assertEquals(mockOrderList, result);
	}
	
	@Test
	public void viewOrderDetail_should_return_null_if_logined_user_is_not_the_owner_of_this_order() {
		when(session.getAttribute("user")).thenReturn(mockUser);
		when(mockUser.getId()).thenReturn(mockUserId);
		when(orderDao.getEntity(orderId)).thenReturn(order);
		when(order.getUser()).thenReturn(mockUser2);
		when(mockUser2.getId()).thenReturn(orderUserId);
		
		Order result = viewHistoryLogic.viewOrderDetail(orderId, session);
		
		assertNull(result);
	}
	
	@Test
	public void viewOrderDetail_should_return_an_order_if_loggined_user_is_the_orderOwner() {
		when(session.getAttribute("user")).thenReturn(mockUser);
		when(mockUser.getId()).thenReturn(orderUserId);
		when(orderDao.getEntity(orderId)).thenReturn(order);
		when(order.getUser()).thenReturn(mockUser2);
		when(mockUser2.getId()).thenReturn(orderUserId);
		
		Order result = viewHistoryLogic.viewOrderDetail(orderId, session);
		
		assertEquals(order, result);
	}
	
	@Test
	public void viewOrderDetail_should_return_null_if_the_orderId_is_not_exsist() {
		when(orderDao.getEntity(orderId)).thenReturn(null);
		
		Order result = viewHistoryLogic.viewOrderDetail(orderId, session);
		
		assertNull(result);
	}
}
