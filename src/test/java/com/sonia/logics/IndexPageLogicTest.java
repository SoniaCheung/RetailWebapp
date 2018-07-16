package com.sonia.logics;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import com.sonia.daos.ProductDao;
import com.sonia.daos.ProductImageDao;
import com.sonia.displayObjectFactories.IndexPageBasicProductDisplayFactory;
import com.sonia.displayObjects.IndexPageBasicProductDisplay;
import com.sonia.entities.Product;
import com.sonia.entities.ProductImage;
import com.sonia.pageLogics.IndexPageLogic;

public class IndexPageLogicTest {

	@Mock
	ProductDao productDao;
	@Mock
	ProductImageDao productImageDao;
	@Mock
	List<Product> mockProductList;
	@Mock
	List<ProductImage> mockProductImageList;
	@Mock
	Iterator<Product> productIterator;
	@Mock
	Product mockProduct;
	@Mock
	ProductImage mockProductImage;
	@Mock
	IndexPageBasicProductDisplayFactory indexPageBasicProductDisplayFactory;
	@InjectMocks
	IndexPageLogic indexPageLogic = new IndexPageLogic();
	
	
	int productId = 101;
	String productName = "Product1";
	double price = 20.2;
	String thumbnailLink = "mock thumbnail link";
	String productImage2 = "mock product image two";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllIndexPageBasixDisplayObjects_should_return_an_array_of_IndexPageBasicProductDisplay() {
		setupDaosReturn();
		List<IndexPageBasicProductDisplay> result = indexPageLogic.getAllIndexPageBasixDisplayObjects();
		verify(productDao).listEntities();
		verify(productImageDao).listProductImagesByProductId(productId);
		verify(indexPageBasicProductDisplayFactory).createIndexPageBasicProductDisplay(productId, productName, price, thumbnailLink);
		assertEquals(1, result.size());
	}

	private void setupDaosReturn() {
		when(mockProductList.iterator()).thenReturn(productIterator);
		when(productIterator.hasNext()).thenReturn(true).thenReturn(false);
		when(productIterator.next()).thenReturn(mockProduct);
		when(productDao.listEntities()).thenReturn(mockProductList);
		
		when(productImageDao.listProductImagesByProductId(productId)).thenReturn(mockProductImageList);
		when(mockProductImageList.size()).thenReturn(1);
		when(mockProductImageList.get(0)).thenReturn(mockProductImage);
		
		when(mockProduct.getId()).thenReturn(productId);
		when(mockProduct.getProductName()).thenReturn(productName);
		when(mockProduct.getPrice()).thenReturn(price);
		when(mockProductImage.getImageLink()).thenReturn(thumbnailLink);
	}
}
