package com.sonia.displayObjectFactories;

import com.sonia.displayObjects.IndexPageBasicProductDisplay;

public class IndexPageBasicProductDisplayFactory {
	public IndexPageBasicProductDisplay createIndexPageBasicProductDisplay(int productId, String productName, double price, String thumbnail) {
		return new IndexPageBasicProductDisplay(productId, productName, price, thumbnail);
	}
}
