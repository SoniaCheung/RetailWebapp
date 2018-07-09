package com.sonia.daos;

import com.sonia.entities.Product;

public class ProductDao extends GenericDao<Product>{

	public ProductDao() {
		super(Product.class);
	}

}
