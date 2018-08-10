package com.sonia.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.ModelMap;

import com.sonia.entities.Order;
import com.sonia.entities.OrderedProduct;
import com.sonia.pageLogics.PlaceOrderLogic;

@Controller
@SessionAttributes("order")
public class PlaceOrderController {

	@Resource(name="placeOrderLogic")
	PlaceOrderLogic placeOrderLogic;
	
	@RequestMapping(value="/deliveryInfoForm", method = RequestMethod.POST)
	public String goToDeliveryInfoFormPage(HttpServletRequest request, HttpSession session) {
		return "deliveryInfoForm";
	}

	@RequestMapping(value="/confirmationPage", method = RequestMethod.POST)
	public String goToConfirmationPage(ModelMap model, HttpServletRequest request, HttpSession session) {
		List<OrderedProduct> orderedProducts = placeOrderLogic.createOrderedProductByShoppingCart(session);
		Order createdOrder = placeOrderLogic.createNewOrderByInfo(request, session, orderedProducts);
		model.addAttribute("order", createdOrder);
		
		request.setAttribute("order", createdOrder);
		
		return "confirmationPage";
	}

	@RequestMapping(value="/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(ModelMap model, HttpServletRequest request, HttpSession session) {
		Order order = (Order) model.get("order");
		
		Order confirmedOrder = placeOrderLogic.confirmOrder(order);
		
		request.setAttribute("confirmedOrder", confirmedOrder);
		return "orderSummaryPage";
	}

}
