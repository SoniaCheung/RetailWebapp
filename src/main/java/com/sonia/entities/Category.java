package com.sonia.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "RWA_CATEGORY")
public class Category implements GenericEntity {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private int id;
	@Column(name = "category_name")
	private String categoryName;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "category_product", joinColumns = { 
			@JoinColumn(name = "category_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "product_id") })
	private List<Product> productList;

	public Category() {
		super();
	}

	public Category(String categoryName, List<Product> productList) {
		super();
		this.categoryName = categoryName;
		this.productList = productList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
