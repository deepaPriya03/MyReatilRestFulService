package com.retail.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.retail.model.PriceDetail;
import com.retail.model.Product;

public class ProductMapper extends DataMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();

		product.setId(rs.getString("id"));
		product.setName(rs.getString("name"));
		
		PriceDetail priceDetail = new PriceDetail();
		priceDetail.setValue(rs.getDouble("value"));
		priceDetail.setCurrency_code(rs.getString("currency_code"));
		product.setCurrent_price(priceDetail);
		return product;
	}
	
	

}
