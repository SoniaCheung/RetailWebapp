package com.sonia.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="RWA_PRODUCT")
public class Product implements GenericEntity{
	
	@Id
	@GeneratedValue
	@Column(name = "product_id")
	private int id;
	@Column(name="product_name")
	private String productName;
	@Column(name="product_description")
	private String productDescription;
	@Column(name="stock")
	private int stock;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "productList")
	private List<Category> categoryList;
	
	public Product() {
		super();
	}

	public Product(String productName, String productDescription, int stock, List<Category> categoryList) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.stock = stock;
		this.categoryList = categoryList;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
