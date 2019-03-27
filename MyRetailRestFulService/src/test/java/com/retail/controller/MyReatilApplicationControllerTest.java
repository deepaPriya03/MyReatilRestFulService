package com.retail.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.retail.model.PriceDetail;
import com.retail.model.Product;
import com.retail.service.MyRetailAppService;

public class MyReatilApplicationControllerTest {
	
	private MyReatilApplicationController myReatilApplicationController;
	
	@Mock
	private MyRetailAppService myRetailAppService;
	
	@Before
	public void setUp() throws Exception {
		myReatilApplicationController = new MyReatilApplicationController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(myReatilApplicationController, "myRetailAppService", myRetailAppService);
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
		Mockito.when(this.myRetailAppService.getProductDetails(Mockito.anyString())).thenReturn(expectedProduct);
		
		Product actualProduct = this.myReatilApplicationController.getProductDetails(id);
		Assert.assertEquals(expectedProduct.getName(), actualProduct.getName());
	}
	
	@Test
	public void testRetrieveProductName(){
		String id="15117729";
		
		String expectedProductName = "Apple iPhone 7 128GB";
		Mockito.when(this.myRetailAppService.retrieveProductName(Mockito.anyString())).thenReturn(expectedProductName);
		
		String actualProductName = this.myReatilApplicationController.retrieveProductName(id);
		Assert.assertEquals(expectedProductName, actualProductName);
	}
	
	@Test
	public void testUpdateProductPrice(){
		String id="15117729";
		String value = "99.99";
		
		Product expectedProduct = new Product();
		expectedProduct.setId(id);
		expectedProduct.setName("Apple iPhone 7 128GB");
		PriceDetail expectedPriceDetail = new PriceDetail();
		expectedPriceDetail.setCurrency_code("USD");
		expectedPriceDetail.setValue(Double.parseDouble(value));
		expectedProduct.setCurrent_price(expectedPriceDetail);
		Mockito.when(this.myRetailAppService.updateProductPrice(Mockito.anyString(), Mockito.anyDouble())).thenReturn(expectedProduct);
		
		Product actualProduct = this.myReatilApplicationController.updateProductPrice(id, value);
		Assert.assertEquals(expectedProduct.getCurrent_price().getValue(), actualProduct.getCurrent_price().getValue());
	}

}
