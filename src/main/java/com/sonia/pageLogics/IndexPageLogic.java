package com.sonia.pageLogics;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.sonia.daos.CategoryDao;
import com.sonia.daos.ProductDao;
import com.sonia.daos.ProductImageDao;
import com.sonia.displayObjectFactories.IndexPageBasicProductDisplayFactory;
import com.sonia.displayObjects.IndexPageBasicProductDisplay;
import com.sonia.entities.Category;
import com.sonia.entities.Product;
import com.sonia.entities.ProductImage;


public class IndexPageLogic {

	@Resource(name="productDao")
	ProductDao productDao;
	@Resource(name="productImageDao")
	ProductImageDao productImageDao;
	
	@Resource(name="categoryDao")
	CategoryDao categoryDao;
	
	@Resource(name="indexPageBasicProductDisplayFactory")
	IndexPageBasicProductDisplayFactory indexPageBasicProductDisplayFactory;
	
	public List<IndexPageBasicProductDisplay> getAllIndexPageBasicDisplayObjects() {
		List<IndexPageBasicProductDisplay> allDisplayProducts = new ArrayList<>();
		List<Product> products = productDao.listEntities();
		return setDisplayProdutsByProductList(allDisplayProducts, products);
	}

	public List<IndexPageBasicProductDisplay> getIndexPageBasicDisplayObjectsByCategory(String categoryName) {
		List<IndexPageBasicProductDisplay> displayCategoryProducts = new ArrayList<>();
		Category category = categoryDao.getCategoryByName(categoryName);
		List<Product> categoryProducts  = new ArrayList<>();
		if(category != null) {
			categoryProducts = category.getProductList();
		}
		return setDisplayProdutsByProductList(displayCategoryProducts, categoryProducts);
	}
	
	private List<IndexPageBasicProductDisplay> setDisplayProdutsByProductList(List<IndexPageBasicProductDisplay> displayProducts, List<Product> products){
		for(Product p : products) {
			int productId = p.getId();
			String productName = p.getProductName();
			double price = p.getPrice();
			String thumbnail = "";
			List<ProductImage> productImages = productImageDao.listProductImagesByProductId(productId);
			if (productImages.size() > 0)
				thumbnail = productImages.get(0).getImageLink();
			IndexPageBasicProductDisplay temp = indexPageBasicProductDisplayFactory.createIndexPageBasicProductDisplay(productId, productName, price, thumbnail);
			displayProducts.add(temp);
		}
		
		return displayProducts;
		
	}

}
