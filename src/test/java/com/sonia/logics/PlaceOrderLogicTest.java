package com.sonia.logics;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sonia.daos.OrderDao;
import com.sonia.daos.OrderedProductDao;
import com.sonia.daos.ProductDao;
import com.sonia.displayObjects.ShoppingCart;
import com.sonia.entities.Order;
import com.sonia.entities.OrderedProduct;
import com.sonia.entities.Product;
import com.sonia.entities.User;
import com.sonia.factories.OrderFactory;
import com.sonia.factories.OrderedProductFactory;
import com.sonia.pageLogics.PlaceOrderLogic;

public class PlaceOrderLogicTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@Mock
	ShoppingCart mockShoppingCart;
	@Mock
	OrderedProductFactory orderedProductFactory;
	@Mock
	OrderFactory orderFactory;
	@Mock
	Map<Product, Integer> mockShoppingCartProductMap;
	@Mock
	Set<Product> mockProductKeys;
	@Mock
	Iterator<Product> mockProductKeyItertor;
	@Mock
	Product mockProduct;
	@Mock
	Product mockProduct2;
	@Mock
	Map.Entry<Product, Integer> mockEntry;
	@Mock
	Map.Entry<Product, Integer> mockEntry2;
	@Mock
	List<OrderedProduct> mockOrderedProducts;
	@Mock
	Iterator<OrderedProduct> mockOrderedProductsIterator;
	@Mock
	OrderedProduct mockOrderedProduct;
	@Mock
	OrderedProduct mockOrderedProduct2;
	@Mock
	OrderedProduct mockConfirmedOrderedProduct;
	@Mock
	OrderedProduct mockConfirmedOrderedProduct2;
	@Mock
	OrderDao orderDao;
	@Mock
	OrderedProductDao orderedProductDao;
	@Mock
	ProductDao productDao;
	@Mock
	User mockUser;
	@Mock
	Order mockOrder;
	@Mock
	Order mockConfirmedOrder;
	
	@InjectMocks
	PlaceOrderLogic placeOrderLogic = new PlaceOrderLogic();
	
	int mockProductId = 9;
	int mockProductId2 = 10;
	Integer quantity = 5;
	Integer quantity2 = 10;
	double price = 30.5;
	double price2 = 8999.9;
	int productOriginalStock = 500;
	int productOriginalStock2 = 800;
	
	String deliveryAddress = "123 Road, ABC Street, Hong Kong";
	String remarks = "Remarks";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void createOrderedProductByShoppingCart_shoudl_return_a_list_of_orderedProducts() {
		when(session.getAttribute("shoppingCart")).thenReturn(mockShoppingCart);
		when(mockShoppingCart.getProductMap()).thenReturn(mockShoppingCartProductMap);
		when(mockShoppingCartProductMap.keySet()).thenReturn(mockProductKeys);
		when(mockProductKeys.iterator()).thenReturn(mockProductKeyItertor);
		when(mockProductKeyItertor.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(mockProductKeyItertor.next()).thenReturn(mockProduct).thenReturn(mockProduct2);
		when(mockShoppingCartProductMap.get(mockProduct)).thenReturn(quantity);
		when(mockShoppingCartProductMap.get(mockProduct2)).thenReturn(quantity2);
		setupMockProducts();
		
		List<OrderedProduct> result = placeOrderLogic.createOrderedProductByShoppingCart(session);
	
		verify(orderedProductFactory).createOrderedProduct(mockProduct, quantity, price);
		verify(orderedProductFactory).createOrderedProduct(mockProduct2, quantity2, price2);
		assertEquals(2, result.size());
	}

	@Test
	public void createNewOrderByInfo_should_return_an_order_with_correct_info() {
		when(session.getAttribute("user")).thenReturn(mockUser);
		when(request.getParameter("address")).thenReturn(deliveryAddress);
		when(request.getParameter("remarks")).thenReturn(remarks);
		when(orderFactory.createOrder(mockUser, mockOrderedProducts, deliveryAddress, null, remarks)).thenReturn(mockOrder);
		
		Order result = placeOrderLogic.createNewOrderByInfo(request, session, mockOrderedProducts);
	
		verify(orderFactory).createOrder(mockUser, mockOrderedProducts, deliveryAddress, null, remarks);
		assertNotNull(result);
	}
	
	@Test
	public void confirmOrder_should_add_order_to_database_update_product_stock_and_clean_up_the_shopping_cartthen_return_the_result() {
		setupMockProducts();
		
		when(orderDao.addOrUpdateEntity(mockOrder)).thenReturn(mockConfirmedOrder);
		when(mockOrder.getOrderedProductList()).thenReturn(mockOrderedProducts);
		when(mockOrderedProducts.iterator()).thenReturn(mockOrderedProductsIterator);
		when(mockOrderedProductsIterator.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(mockOrderedProductsIterator.next()).thenReturn(mockOrderedProduct).thenReturn(mockOrderedProduct2);
		when(orderedProductDao.addOrUpdateEntity(mockOrderedProduct)).thenReturn(mockConfirmedOrderedProduct);
		when(orderedProductDao.addOrUpdateEntity(mockOrderedProduct2)).thenReturn(mockConfirmedOrderedProduct2);
		
		when(mockOrderedProduct.getProduct()).thenReturn(mockProduct);
		when(mockOrderedProduct2.getProduct()).thenReturn(mockProduct2);
		when(mockOrderedProduct.getQuantity()).thenReturn(quantity);
		when(mockOrderedProduct2.getQuantity()).thenReturn(quantity2);
		when(productDao.getEntity(mockProductId)).thenReturn(mockProduct);
		when(productDao.getEntity(mockProductId2)).thenReturn(mockProduct2);
		
		Order result = placeOrderLogic.confirmOrder(session, mockOrder);
		
		verify(orderDao).addOrUpdateEntity(mockOrder);
		verify(orderedProductDao).addOrUpdateEntity(mockOrderedProduct);
		verify(orderedProductDao).addOrUpdateEntity(mockOrderedProduct2);
		verify(mockProduct).setStock(productOriginalStock - quantity);
		verify(mockProduct2).setStock(productOriginalStock2 - quantity2);
		verify(productDao).addOrUpdateEntity(mockProduct);
		verify(productDao).addOrUpdateEntity(mockProduct2);
		verify(mockConfirmedOrder).setOrderedProductList(any());
		verify(session).removeAttribute("shoppingCart");
		assertEquals(mockConfirmedOrder, result);
	}
	
	private void setupMockProducts() {
		when(mockProduct.getId()).thenReturn(mockProductId);
		when(mockProduct2.getId()).thenReturn(mockProductId2);
		when(mockProduct.getPrice()).thenReturn(price);
		when(mockProduct2.getPrice()).thenReturn(price2);
		when(mockProduct.getStock()).thenReturn(productOriginalStock);
		when(mockProduct2.getStock()).thenReturn(productOriginalStock2);
	}
}
