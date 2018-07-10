package com.sonia.factories;

import com.sonia.entities.Coupon;

public class CouponFactory {
	public Coupon createCoupon(String couponCode, double couponRate) {
		return new Coupon(couponCode, couponRate);
	}
}
