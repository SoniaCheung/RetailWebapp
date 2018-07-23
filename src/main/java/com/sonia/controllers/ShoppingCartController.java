package com.sonia.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {

	@RequestMapping(value="/shoppingCart")
	public String goToShoppingCart(HttpServletRequest request, HttpSession session) {
		return "shoppingCart";
	}

}
