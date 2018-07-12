package com.sonia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RWA_PRODUCT_IMAGE")
public class ProductImage implements GenericEntity{
	@Id
	@GeneratedValue
	@Column(name = "product_image_id")
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
	@Column
	private String image_link;
	
	public ProductImage() {
		super();
	}
	
	public ProductImage(Product product, String image_link) {
		super();
		this.product = product;
		this.image_link = image_link;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getImage_link() {
		return image_link;
	}
	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}
}
