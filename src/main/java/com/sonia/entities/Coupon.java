package com.sonia.entities;

public class Coupon implements GenericEntity{

	private long id;
	private String couponCode;
	private double couponRate;
	
	public Coupon(String couponCode, double couponRate) {
		super();
		this.couponCode = couponCode;
		this.couponRate = couponRate;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public double getCouponRate() {
		return couponRate;
	}
	public void setCouponRate(double couponRate) {
		this.couponRate = couponRate;
	}
}
