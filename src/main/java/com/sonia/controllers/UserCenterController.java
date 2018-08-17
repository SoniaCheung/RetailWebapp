package com.sonia.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sonia.entities.Order;
import com.sonia.entityHandlers.OrderHandler;
import com.sonia.pageLogics.ViewHistoryLogic;

@Controller
public class UserCenterController {

	@Resource(name="viewHistoryLogic")
	ViewHistoryLogic viewHistoryLogic;
	@Resource(name="orderHandler")
	OrderHandler orderHandler;
	
	@RequestMapping(value="/userCenter")
	public String goToUserCenter(HttpServletRequest request, HttpSession session) {
		return "userCenter";
	}

	@RequestMapping(value="/viewPersonalOrderHistory", method = RequestMethod.POST)
	public String viewPersonalOrderHistory(HttpServletRequest request, HttpSession session) {
		List<Order> orderList = viewHistoryLogic.personalOrderHistory(session);
		
		request.setAttribute("orders", orderList);
		return "viewHistory";
	}

	@RequestMapping(value="/viewOrder")
	public String viewOrderDetail(@RequestParam("id") int orderId, HttpServletRequest request, HttpSession session){
		Order order = viewHistoryLogic.viewOrderDetail(orderId, session);
		request.setAttribute("order", order);
		double totalAmount = orderHandler.calculateOrderTotalCost(order);
		request.setAttribute("totalAmount", totalAmount);
		
		return "viewOrderDetail";
	}
}
