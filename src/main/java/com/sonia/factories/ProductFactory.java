package com.sonia.factories;

import java.util.List;

import com.sonia.entities.Category;
import com.sonia.entities.Product;
import com.sonia.entities.ProductImage;

public class ProductFactory {
	public Product createProduct(String productName, String productDescription, int stock, double price,
			List<ProductImage> productImageList, List<Category> categoryList) {
		return new Product(productName, productDescription, stock, price, productImageList, categoryList);
	}
}
