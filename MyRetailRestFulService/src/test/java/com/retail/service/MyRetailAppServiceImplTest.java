package com.retail.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.retail.model.PriceDetail;
import com.retail.model.Product;
import com.retail.repository.MyRetailAppRepository;

public class MyRetailAppServiceImplTest {

	private MyRetailAppServiceImpl myRetailAppServiceImpl;

	@Mock
	private MyRetailAppRepository myRetailAppRepository;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private Environment env;

	@Before
	public void setUp() throws Exception {
		myRetailAppServiceImpl = new MyRetailAppServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(myRetailAppServiceImpl, "myRetailAppRepository", myRetailAppRepository);
		ReflectionTestUtils.setField(myRetailAppServiceImpl, "restTemplate", restTemplate);
		ReflectionTestUtils.setField(myRetailAppServiceImpl, "env", env);
	}

	@Test
	public void testGetProductDetails() {
		String id = "15117729";

		Product expectedProduct = new Product();
		expectedProduct.setId(id);
		expectedProduct.setName("Apple iPhone 7 128GB");
		PriceDetail expectedPriceDetail = new PriceDetail();
		expectedPriceDetail.setCurrency_code("USD");
		expectedPriceDetail.setValue(499.99);
		expectedProduct.setCurrent_price(expectedPriceDetail);
		Mockito.when(this.myRetailAppRepository.getProductDetails(Mockito.anyString())).thenReturn(expectedProduct);

		Product actualProduct = this.myRetailAppServiceImpl.getProductDetails(id);
		Assert.assertEquals(expectedProduct.getName(), actualProduct.getName());
	}

	@Test
	public void testRetrieveProductName_Exception() {
		String id = "151177291";
		Mockito.when(this.env.getProperty(Mockito.anyString())).thenReturn("");

		Mockito.when(this.restTemplate.getForObject(Mockito.anyString(), Mockito.any(), Mockito.any(Object[].class)))
				.thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND)).thenReturn("");
		String name = this.myRetailAppServiceImpl.retrieveProductName(id);
		Assert.assertEquals("", name);
	}

	@Test
	public void testUpdateProductPrice() {
		String id = "15117729";
		Double value = 99.99;

		Product expectedProduct = new Product();
		expectedProduct.setId(id);
		expectedProduct.setName("Apple iPhone 7 128GB");
		PriceDetail expectedPriceDetail = new PriceDetail();
		expectedPriceDetail.setCurrency_code("USD");
		expectedPriceDetail.setValue(value);
		expectedProduct.setCurrent_price(expectedPriceDetail);
		Mockito.when(this.myRetailAppRepository.updateProductPrice(Mockito.anyString(), Mockito.anyDouble()))
				.thenReturn(expectedProduct);

		Product actualProduct = this.myRetailAppServiceImpl.updateProductPrice(id, value);
		Assert.assertEquals(expectedProduct.getCurrent_price().getValue(), actualProduct.getCurrent_price().getValue());
	}

}
