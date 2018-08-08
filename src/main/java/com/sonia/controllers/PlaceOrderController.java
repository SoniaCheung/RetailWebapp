package com.sonia.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlaceOrderController {

	@RequestMapping(value="/deliveryInfoForm")
	public String goToDeliveryInfoFormPage(HttpServletRequest request, HttpSession session) {
		return "deliveryInfoForm";
	}

}
