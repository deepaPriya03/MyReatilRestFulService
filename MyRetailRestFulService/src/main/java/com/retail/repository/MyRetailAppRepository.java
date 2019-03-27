package com.retail.repository;

import com.retail.model.Product;

public interface MyRetailAppRepository {
	
	public Product getProductDetails(String id);
	public Product getPricingInformation(String id, String name);
	public Product updateProductPrice(String id, Double value);
	
}
