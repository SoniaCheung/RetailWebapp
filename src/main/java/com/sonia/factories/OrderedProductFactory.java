package com.sonia.factories;

import com.sonia.entities.OrderedProduct;
import com.sonia.entities.Product;

public class OrderedProductFactory {
	public OrderedProduct createOrderedProduct(Product product, int quantity, double price) {
		return new OrderedProduct(product, quantity, price);
	}
}
