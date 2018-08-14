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

import com.sonia.entities.Order;
import com.sonia.entities.User;
import com.sonia.pageLogics.ViewHistoryLogic;

public class ViewHistoryLogicTest {

	@Mock
	HttpSession session;
	@Mock
	User mockUser;
	@Mock
	List<Order> mockOrderList;
	@InjectMocks
	ViewHistoryLogic viewHistoryLogic = new ViewHistoryLogic();
	
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
}
