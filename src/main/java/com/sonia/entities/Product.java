package com.sonia.entities;

import java.util.List;

public class Product implements GenericEntity{

	private long id;
	private String productName;
	private String productDescription;
	private int stock;
	private List<Category> categoryList;
	
	public Product(String productName, String productDescription, int stock, List<Category> categoryList) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.stock = stock;
		this.categoryList = categoryList;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
