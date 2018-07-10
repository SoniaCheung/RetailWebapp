package com.sonia.factories;

import java.util.List;

import com.sonia.entities.Category;
import com.sonia.entities.Product;

public class ProductFactory {
	public Product createProduct(String productName, String productDescription, int stock, List<Category> categoryList) {
		return new Product(productName, productDescription, stock, categoryList);
	}
}
