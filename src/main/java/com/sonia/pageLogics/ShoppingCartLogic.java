package com.sonia.pageLogics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
			ShoppingCart newShoppingCart = createAndSetupNewShoppingCart(product, quantity);
			session.setAttribute("shoppingCart", newShoppingCart);
		} else {	
			ShoppingCart currentShoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
			if(!checkMapContainsProduct(currentShoppingCart.getProductMap(), product)) {
				currentShoppingCart.getProductMap().put(product, quantity);
			} else {
				for(Entry<Product, Integer> entry : currentShoppingCart.getProductMap().entrySet()) {
					if (entry.getKey().getId() == product.getId()) {
						entry.setValue(entry.getValue() + quantity);
						break;
					}
				}
			}
			currentShoppingCart.updateTotalAmount();
		}
	}
	
	private ShoppingCart createAndSetupNewShoppingCart(Product product, Integer quantity) {
		ShoppingCart newShoppingCart = shoppingCartFactory.createShoppingCart();
		newShoppingCart.setProductMap(new HashMap<Product, Integer>());
		newShoppingCart.getProductMap().put(product, quantity);
		newShoppingCart.updateTotalAmount();
		return newShoppingCart;
	}
	
	private boolean checkMapContainsProduct(Map<Product, Integer> productMap, Product targetProduct) {
		for(Product mapProduct : productMap.keySet()) {
			if (mapProduct.getId() == targetProduct.getId()) {
				return true;
			}
		}
		return false;
	}

}
