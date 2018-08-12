package com.sonia.entityHandlers;

import java.util.List;

import com.sonia.entities.Order;
import com.sonia.entities.OrderedProduct;

public class OrderHandler {

	public double calculateOrderTotalCost(Order order) {
		
		double result = 0.0;
		List<OrderedProduct> orderedProductList = order.getOrderedProductList();
		
		for(OrderedProduct product : orderedProductList) {
			result += product.getPrice() * product.getQuantity();
		}
		
		double couponDiscountRate = 1.0;
		if(order.getCoupon() != null) {
			couponDiscountRate = order.getCoupon().getCouponRate();
		}
		
		return result * couponDiscountRate;
	}

}
