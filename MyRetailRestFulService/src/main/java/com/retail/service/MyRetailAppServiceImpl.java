package com.retail.service;

import java.net.URI;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.retail.model.Product;
import com.retail.repository.MyRetailAppRepository;
import com.retail.repository.MyRetailAppRepositoryImpl;

@Service("myRetailAppService")
public class MyRetailAppServiceImpl implements MyRetailAppService {

	private static final Logger LOGGER = Logger.getLogger(MyRetailAppRepositoryImpl.class);
	
	@Autowired
	private MyRetailAppRepository myRetailAppRepository;
	
	@Autowired
	private Environment env;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Product getProductDetails(String id) {
		LOGGER.info("Executing MyRetailAppServiceImpl getProductDetails() method");
		return this.myRetailAppRepository.getProductDetails(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String retrieveProductName(String id) throws HttpClientErrorException {
		LOGGER.info("Executing MyRetailAppServiceImpl retrieveProductName() method");
		String URL = env.getProperty("external.api.url").concat(id).concat(env.getProperty("external.api.url.params"));
		String name = "";
		try{

			URI url = new URI("https://redsky.target.com/v1/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");
			
			ResponseEntity<String> responseBody = restTemplate.getForEntity(url, String.class);
            name = responseBody.getBody().substring(1013, 1039);
			
		}catch(Exception e){
			LOGGER.debug("Error occured in retrieving Name from External API :: "+e);
		}
		return name;
	}
	@Override
	public Product readPricingInformation(String id) {
		LOGGER.info("Executing MyRetailAppServiceImpl readPricingInformation() method");
		String name = retrieveProductName(id);
		LOGGER.info("Name of the Product with Id = "+id+" is :: "+name);
		if("".equals(name) || name == null){
			return new Product();
		}
		return this.myRetailAppRepository.getPricingInformation(id, name);
	}
	
	@Override
	public Product updateProductPrice(String id, Double value) {
		LOGGER.info("Executing MyRetailAppServiceImpl updateProductPrice() method");
		return this.myRetailAppRepository.updateProductPrice(id, value);
	}
	
}
