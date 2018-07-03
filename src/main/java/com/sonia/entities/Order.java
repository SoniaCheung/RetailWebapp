package com.sonia.entities;

import java.util.List;

public class Order implements GenericEntity{

	private long id;
	private User user;
	private List<OrderedProduct> orderedProductList;
	private String deliveryAddress;
	private Coupon coupon;
	private String remarks;
	
	public Order(User user, List<OrderedProduct> orderedProductList, String deliveryAddress, Coupon coupon,
			String remarks) {
		super();
		this.user = user;
		this.orderedProductList = orderedProductList;
		this.deliveryAddress = deliveryAddress;
		this.coupon = coupon;
		this.remarks = remarks;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderedProduct> getOrderedProductList() {
		return orderedProductList;
	}
	public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
		this.orderedProductList = orderedProductList;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
