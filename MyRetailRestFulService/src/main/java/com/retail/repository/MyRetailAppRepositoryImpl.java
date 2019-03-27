package com.retail.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.retail.mapper.ProductMapper;
import com.retail.model.Product;

@Repository("myRetailAppRepository")
public class MyRetailAppRepositoryImpl implements MyRetailAppRepository {
	
	private static final Logger LOGGER = Logger.getLogger(MyRetailAppRepositoryImpl.class);
	
	@Autowired
	@Qualifier("jTemplate")
	private JdbcTemplate jTemplate;
	
	@Override
	public Product getProductDetails(String id) {
		LOGGER.info("Executing MyRetailAppRepositoryImpl getProductDetails() method :: "+id);
		return jTemplate.queryForObject(Queries.PRODUCT_DETAILS_QUERY, new Object[]{id}, new ProductMapper());
	}

	@Override
	public Product getPricingInformation(String id, String name) {
		Product product = jTemplate.queryForObject(Queries.PRICE_DETAILS_QUERY, new Object[]{id, name}, new ProductMapper());
		return product;
	}

	@Override
	public Product updateProductPrice(String id, Double value) {
		int updateRecordCount = this.jTemplate.update(Queries.UPDATE_PRODUCT_PRICE_QUERY, new Object[]{value, id});
		if(updateRecordCount <= 0){
			return new Product();
		}
		return getProductDetails(id);
	}

}
