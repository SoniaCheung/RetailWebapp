package com.sonia.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlaceOrderController {

	@RequestMapping(value="/deliveryInfoForm", method = RequestMethod.POST)
	public String goToDeliveryInfoFormPage(HttpServletRequest request, HttpSession session) {
		return "deliveryInfoForm";
	}

}
