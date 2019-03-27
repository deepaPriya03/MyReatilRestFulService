package com.retail.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.retail.mapper.ProductMapper;
import com.retail.model.PriceDetail;
import com.retail.model.Product;
import com.retail.repository.MyRetailAppRepository;
import com.retail.repository.MyRetailAppRepositoryImpl;

public class MyRetailAppRepositoryImplTest { 
	
	@Mock
	private JdbcTemplate jTemplate;

	private MyRetailAppRepository myRetailAppRepository;
	
	@Before
	public void setUp() throws Exception {
		myRetailAppRepository = new MyRetailAppRepositoryImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(myRetailAppRepository, "jTemplate", jTemplate);
	}
	
	@Test
	public void testGetProductDetails(){
		String id="15117729";
		
		Product expectedProduct = new Product();
		expectedProduct.setId(id);
		expectedProduct.setName("Apple iPhone 7 128GB");
		PriceDetail expectedPriceDetail = new PriceDetail();
		expectedPriceDetail.setCurrency_code("USD");
		expectedPriceDetail.setValue(499.99);
		expectedProduct.setCurrent_price(expectedPriceDetail);
		Mockito.when(this.jTemplate.queryForObject(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(ProductMapper.class))).thenReturn(expectedProduct);
		
		Product actualProduct = this.myRetailAppRepository.getProductDetails(id);
		Assert.assertEquals(expectedProduct.getName(), actualProduct.getName());
	}

	@Test
	public void testGetPricingInformation(){
		String id="15117729";
		String name = "Apple iPhone 7 128GB";
		
		Product expectedProduct = new Product();
		expectedProduct.setId(id);
		expectedProduct.setName(name);
		PriceDetail expectedPriceDetail = new PriceDetail();
		expectedPriceDetail.setCurrency_code("USD");
		expectedPriceDetail.setValue(499.99);
		expectedProduct.setCurrent_price(expectedPriceDetail);
		Mockito.when(this.jTemplate.queryForObject(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(ProductMapper.class))).thenReturn(expectedProduct);
		
		Product actualProduct = this.myRetailAppRepository.getPricingInformation(id, name);
		Assert.assertEquals(expectedProduct.getCurrent_price().getValue(), actualProduct.getCurrent_price().getValue());
	}
	
}
