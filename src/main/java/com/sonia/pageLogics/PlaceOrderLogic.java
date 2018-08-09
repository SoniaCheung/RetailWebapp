package com.sonia.pageLogics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sonia.displayObjects.ShoppingCart;
import com.sonia.entities.Order;
import com.sonia.entities.OrderedProduct;
import com.sonia.entities.Product;
import com.sonia.entities.User;
import com.sonia.factories.OrderFactory;
import com.sonia.factories.OrderedProductFactory;

public class PlaceOrderLogic {

	@Resource(name="orderedProductFactory")
	OrderedProductFactory orderedProductFactory;
	@Resource(name="orderFactory")
	OrderFactory orderFactory;
	
	public List<OrderedProduct> createOrderedProductByShoppingCart(HttpSession session) {
		List<OrderedProduct> orderedProducts = new ArrayList<>();
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		
		Map<Product, Integer> shoppingCartProductsMap = shoppingCart.getProductMap();
		Set<Product> shoppingCartProductsMapKeys = shoppingCartProductsMap.keySet();
		
		for(Product p : shoppingCartProductsMapKeys) {
			int quantity = shoppingCartProductsMap.get(p);
			double price = p.getPrice();
			OrderedProduct orderedProduct = orderedProductFactory.createOrderedProduct(p, quantity, price);
			orderedProducts.add(orderedProduct);
		}
		
		return orderedProducts;
	}

	public Order createNewOrderByInfo(HttpServletRequest request, HttpSession session, List<OrderedProduct> orderedProducts) {
		User user = (User) session.getAttribute("user");
		String deliveryAddress = request.getParameter("address");
		String remarks = request.getParameter("remarks");
		
		return orderFactory.createOrder(user, orderedProducts, deliveryAddress, null, remarks);
	}

}
