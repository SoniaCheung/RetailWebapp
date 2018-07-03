package com.sonia.entities;

import java.util.List;

public class Category implements GenericEntity{

	private long id;
	private String categoryName;
	private List<Product> productList;
	
	public Category(String categoryName, List<Product> productList) {
		super();
		this.categoryName = categoryName;
		this.productList = productList;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
