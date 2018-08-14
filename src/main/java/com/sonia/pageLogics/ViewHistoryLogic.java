package com.sonia.pageLogics;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sonia.entities.Order;
import com.sonia.entities.User;

public class ViewHistoryLogic {

	public List<Order> personalOrderHistory(HttpSession session) {

		User user = (User) session.getAttribute("user");
		
		return user.getOrderList();
	}

}
