package com.sonia.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sonia.pageLogics.ShoppingCartLogic;

@Controller
public class ShoppingCartController {

	@Resource(name="shoppingCartLogic")
	ShoppingCartLogic shoppingCartLogic;
	
	@RequestMapping(value="/shoppingCart")
	public String goToShoppingCart(HttpServletRequest request, HttpSession session) {
		return "shoppingCart";
	}

	@RequestMapping(value="/addShoppingCart", method = RequestMethod.POST)
	public String addToShoppingCart(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity, HttpServletRequest request, HttpSession session) {
		shoppingCartLogic.addToShoppingCart(productId, quantity, request, session);
		
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}

	
}
