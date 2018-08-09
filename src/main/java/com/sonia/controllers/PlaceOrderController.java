package com.sonia.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sonia.pageLogics.PlaceOrderLogic;

@Controller
public class PlaceOrderController {

	@Resource(name="placeOrderLogic")
	PlaceOrderLogic placeOrderLogic;
	
	@RequestMapping(value="/deliveryInfoForm", method = RequestMethod.POST)
	public String goToDeliveryInfoFormPage(HttpServletRequest request, HttpSession session) {
		return "deliveryInfoForm";
	}

	public String goToConfirmationPage(HttpServletRequest request, HttpSession session) {
		placeOrderLogic.createOrderedProductByShoppingCart(session);
		placeOrderLogic.createNewOrderByInfo(request, session);
		return "confirmationPage";
	}

}
