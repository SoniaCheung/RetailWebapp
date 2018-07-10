package com.sonia.factories;

import java.util.List;

import com.sonia.entities.Coupon;
import com.sonia.entities.Order;
import com.sonia.entities.OrderedProduct;
import com.sonia.entities.User;

public class OrderFactory {
	public Order createOrder(User user, List<OrderedProduct> orderedProductList, String deliveryAddress, Coupon coupon,
			String remarks) {
		return new Order(user, orderedProductList, deliveryAddress, coupon, remarks);
	}
}
