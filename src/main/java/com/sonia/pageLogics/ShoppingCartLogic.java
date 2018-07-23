package com.sonia.pageLogics;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sonia.daos.ProductDao;
import com.sonia.displayObjectFactories.ShoppingCartFactory;
import com.sonia.displayObjects.ShoppingCart;
import com.sonia.entities.Product;

public class ShoppingCartLogic {

	@Resource(name="shoppingCartFactory")
	ShoppingCartFactory shoppingCartFactory;
	@Resource(name="productDao")
	ProductDao productDao;
	
	public void addToShoppingCart(int productId, int quantity, HttpServletRequest request, HttpSession session) {
		Product product = productDao.getEntity(productId);
		if(session.getAttribute("shoppingCart") == null) {
			ShoppingCart newShoppingCart = shoppingCartFactory.createShoppingCart();
			newShoppingCart.setProductMap(new HashMap<Product, Integer>());
			newShoppingCart.getProductMap().put(product, quantity);
			session.setAttribute("shoppingCart", newShoppingCart);
		} else {	
			ShoppingCart currentShoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
			currentShoppingCart.getProductMap().put(product, quantity);
		}
	}

}
