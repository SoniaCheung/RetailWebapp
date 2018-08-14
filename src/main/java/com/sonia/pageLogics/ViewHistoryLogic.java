package com.sonia.pageLogics;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.sonia.daos.OrderDao;
import com.sonia.entities.Order;
import com.sonia.entities.User;

public class ViewHistoryLogic {

	@Resource(name="orderDao")
	OrderDao orderDao;
	
	public List<Order> personalOrderHistory(HttpSession session) {

		User user = (User) session.getAttribute("user");
		
		return user.getOrderList();
	}

	public  Order viewOrderDetail(int orderId, HttpSession session) {
		Order order = orderDao.getEntity(orderId);
		if (order != null) {
			int orderOwnerId = order.getUser().getId();
			User user = (User) session.getAttribute("user");
			int userId = user.getId();
			if (orderOwnerId == userId) {
				return order;
			} else {
				return null;
			}
		} else
			return null;
	}

}
