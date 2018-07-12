package com.sonia.daos;

import com.sonia.entities.Order;

public class OrderDao extends GenericDao<Order>{

	public OrderDao() {
		super(Order.class);
	}

}
