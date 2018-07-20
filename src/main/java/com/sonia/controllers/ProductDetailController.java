package com.sonia.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sonia.daos.ProductDao;
import com.sonia.entities.Product;

@Controller
public class ProductDetailController {
	@Resource(name="productDao")
	ProductDao productDao;

	@RequestMapping(value="/productDetail")
	public String goToProductDetail(@RequestParam("id") int productId, HttpServletRequest request){
		Product product = productDao.getEntity(productId);
		request.setAttribute("product", product);
		return "productDetail";
	}
}
