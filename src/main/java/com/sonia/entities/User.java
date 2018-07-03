package com.sonia.entities;

import java.util.List;

public class User implements GenericEntity{

	private long id;
	private String username;
	private String password;
	private String address;
	private List<Order>orderList;
	
	public User(String username, String password, String address, List<Order> orderList) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.orderList = orderList;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}	
}
