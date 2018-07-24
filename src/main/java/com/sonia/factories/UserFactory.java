package com.sonia.factories;

import java.util.List;

import com.sonia.entities.Order;
import com.sonia.entities.User;

public class UserFactory {

	public User createUser(String username, String password, String email, String address, List<Order> orderList) {
		return new User(username, password, email, address, orderList);
	}
	
	public User createUser() {
		return new User();
	}
}
