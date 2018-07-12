package com.sonia.factories;

import com.sonia.entities.Product;
import com.sonia.entities.ProductImage;

public class ProductImageFactory {
	public ProductImage createProductImage(Product product, String image_link) {
		return new ProductImage(product, image_link);
	}
}
