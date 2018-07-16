package com.sonia.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.sonia.entities.ProductImage;

public class ProductImageDao extends GenericDao<ProductImage>{

	public ProductImageDao() {
		super(ProductImage.class);
	}
	
	public List<ProductImage> listProductImagesByProductId(int productId) {
		List<ProductImage> myList = new ArrayList<>();
		String query = "SELECT t FROM ProductImage t WHERE product_id = " + productId;
		TypedQuery<ProductImage> q1 = em.createQuery(query, ProductImage.class);
		myList = q1.getResultList();
		em.close();
		return myList;	
	}
}
