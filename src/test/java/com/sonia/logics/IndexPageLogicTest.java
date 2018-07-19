package com.sonia.logics;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import com.sonia.daos.CategoryDao;
import com.sonia.daos.ProductDao;
import com.sonia.daos.ProductImageDao;
import com.sonia.displayObjectFactories.IndexPageBasicProductDisplayFactory;
import com.sonia.displayObjects.IndexPageBasicProductDisplay;
import com.sonia.entities.Category;
import com.sonia.entities.Product;
import com.sonia.entities.ProductImage;
import com.sonia.pageLogics.IndexPageLogic;

public class IndexPageLogicTest {

	@Mock
	ProductDao productDao;
	@Mock
	CategoryDao categoryDao;
	@Mock
	ProductImageDao productImageDao;
	@Mock
	List<Product> mockProductList;
	@Mock
	List<ProductImage> mockProductImageList;
	@Mock
	Iterator<Product> productIterator;
	@Mock
	Category mockCategory;
	@Mock
	Product mockProduct;
	@Mock
	ProductImage mockProductImage;
	@Mock
	IndexPageBasicProductDisplayFactory indexPageBasicProductDisplayFactory;
	@InjectMocks
	IndexPageLogic indexPageLogic = new IndexPageLogic();
	
	
	int productId = 101;
	String productName = "product1";
	double price = 20.2;
	String productDescription = "This is a demo product description";
	String thumbnailLink = "mock thumbnail link";
	String productImage2 = "mock product image two";
	
	String categoryName = "clothing";
	
	String searchKeyword1 = "1";
	String searchKeyword2 = "demo";
	String searchKeyword3 = "product";
	String searchKeyword4 = "hello";
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllIndexPageBasixDisplayObjects_should_return_an_array_of_IndexPageBasicProductDisplay() {
		setupDaosReturnForProducts();
		List<IndexPageBasicProductDisplay> result = indexPageLogic.getAllIndexPageBasicDisplayObjects();
		verify(productDao).listEntities();
		verify(productImageDao).listProductImagesByProductId(productId);
		verify(indexPageBasicProductDisplayFactory).createIndexPageBasicProductDisplay(productId, productName, price, thumbnailLink);
		assertEquals(1, result.size());
	}
	
	@Test
	public void getIndexPageBasicDisplayObjectsByCategory_should_return_an_array_of_IndexPageBasicProductDisplay() {
		setupDaosReturnForProducts();
		setupDaoReturnsForCategoryProducts();
		
		List<IndexPageBasicProductDisplay> result = indexPageLogic.getIndexPageBasicDisplayObjectsByCategory(categoryName);
		verify(categoryDao).getCategoryByName(categoryName);
		verify(indexPageBasicProductDisplayFactory).createIndexPageBasicProductDisplay(productId, productName, price, thumbnailLink);
		assertEquals(1, result.size());
	}
	
	@Test
	public void getIndexPageBasicDisplayObjectsBySearchKey_should_search_for_product_name_and_desacription_then_return_an_array_of_IndexPageBasicProductDisplay_if_product_name_matches() {
		setupDaosReturnForProducts();
		
		List<IndexPageBasicProductDisplay> result =  indexPageLogic.getIndexPageBasicDisplayObjectsBySearchKey(searchKeyword1);
		verify(productDao).listEntities();
		verify(indexPageBasicProductDisplayFactory).createIndexPageBasicProductDisplay(productId, productName, price, thumbnailLink);
		assertEquals(1, result.size());
	}

	@Test
	public void getIndexPageBasicDisplayObjectsBySearchKey_should_search_for_product_name_and_desacription_then_return_an_array_of_IndexPageBasicProductDisplay_if_product_description_matches() {
		setupDaosReturnForProducts();
		
		List<IndexPageBasicProductDisplay> result =  indexPageLogic.getIndexPageBasicDisplayObjectsBySearchKey(searchKeyword2);
		verify(productDao).listEntities();
		verify(indexPageBasicProductDisplayFactory).createIndexPageBasicProductDisplay(productId, productName, price, thumbnailLink);
		assertEquals(1, result.size());
	}
	
	@Test
	public void getIndexPageBasicDisplayObjectsBySearchKey_should_search_for_product_name_and_desacription_then_return_an_array_of_IndexPageBasicProductDisplay_if_product_name_and_description_match() {
		setupDaosReturnForProducts();
		
		List<IndexPageBasicProductDisplay> result =  indexPageLogic.getIndexPageBasicDisplayObjectsBySearchKey(searchKeyword3);
		verify(productDao).listEntities();
		verify(indexPageBasicProductDisplayFactory).createIndexPageBasicProductDisplay(productId, productName, price, thumbnailLink);
		assertEquals(1, result.size());
	}
	
	@Test
	public void getIndexPageBasicDisplayObjectsBySearchKey_should_return_empty_list_if_no_product_match_with_the_search_keyword() {
		setupDaosReturnForProducts();
		
		List<IndexPageBasicProductDisplay> result =  indexPageLogic.getIndexPageBasicDisplayObjectsBySearchKey(searchKeyword4);
		verify(productDao).listEntities();
		assertEquals(0, result.size());
	}
	
	private void setupDaosReturnForProducts() {
		when(mockProductList.iterator()).thenReturn(productIterator);
		when(productIterator.hasNext()).thenReturn(true).thenReturn(false);
		when(productIterator.next()).thenReturn(mockProduct);
		when(productDao.listEntities()).thenReturn(mockProductList);
		
		when(productImageDao.listProductImagesByProductId(productId)).thenReturn(mockProductImageList);
		when(mockProductImageList.size()).thenReturn(1);
		when(mockProductImageList.get(0)).thenReturn(mockProductImage);
		
		when(mockProduct.getId()).thenReturn(productId);
		when(mockProduct.getProductName()).thenReturn(productName);
		when(mockProduct.getProductDescription()).thenReturn(productDescription);
		when(mockProduct.getPrice()).thenReturn(price);
		when(mockProductImage.getImageLink()).thenReturn(thumbnailLink);
	}
	
	private void setupDaoReturnsForCategoryProducts() {
		when(categoryDao.getCategoryByName(categoryName)).thenReturn(mockCategory);
		
		when(mockCategory.getProductList()).thenReturn(mockProductList);
	}
}
