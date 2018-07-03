package com.sonia.entities;

public class OrderedProduct implements GenericEntity{

	private long id;
	private Product product;
	private int quantity;
	private double price;
	
	public OrderedProduct(Product product, int quantity, double price) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
