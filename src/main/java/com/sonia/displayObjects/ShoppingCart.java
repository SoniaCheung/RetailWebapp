package com.sonia.displayObjects;

import java.util.Map;

import com.sonia.entities.Product;

public class ShoppingCart {

	private Map<Product, Integer> productMap;

	public Map<Product, Integer> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Product, Integer> productMap) {
		this.productMap = productMap;
	}

}
