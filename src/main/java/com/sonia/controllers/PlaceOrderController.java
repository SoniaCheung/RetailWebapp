package com.sonia.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sonia.entities.Order;
import com.sonia.entities.OrderedProduct;
import com.sonia.pageLogics.PlaceOrderLogic;

@Controller
public class PlaceOrderController {

	@Resource(name="placeOrderLogic")
	PlaceOrderLogic placeOrderLogic;
	
	@RequestMapping(value="/deliveryInfoForm", method = RequestMethod.POST)
	public String goToDeliveryInfoFormPage(HttpServletRequest request, HttpSession session) {
		return "deliveryInfoForm";
	}

	@RequestMapping(value="/confirmationPage", method = RequestMethod.POST)
	public String goToConfirmationPage(HttpServletRequest request, HttpSession session) {
		List<OrderedProduct> orderedProducts = placeOrderLogic.createOrderedProductByShoppingCart(session);
		Order order = placeOrderLogic.createNewOrderByInfo(request, session, orderedProducts);
		
		request.setAttribute("order", order);
		
		return "confirmationPage";
	}

}
