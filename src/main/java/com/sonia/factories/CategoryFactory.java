package com.sonia.factories;

import java.util.List;

import com.sonia.entities.Category;
import com.sonia.entities.Product;

public class CategoryFactory {
	public Category createCategory(String categoryName, List<Product> productList) {
		return new Category(categoryName, productList);
	}
}
