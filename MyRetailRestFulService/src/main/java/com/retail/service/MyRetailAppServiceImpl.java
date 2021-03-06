package com.retail.service;

import java.net.URI;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.retail.model.Product;
import com.retail.repository.MyRetailAppRepository;
import com.retail.repository.MyRetailAppRepositoryImpl;

@Service("myRetailAppService")
public class MyRetailAppServiceImpl implements MyRetailAppService {

	private static final Logger LOGGER = Logger.getLogger(MyRetailAppRepositoryImpl.class);

	@Autowired
	private MyRetailAppRepository myRetailAppRepository;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * Implementation to fetch details of provided product id
	 * 
	 * @param id
	 */
	@Override
	public Product getProductDetails(String id) {
		LOGGER.info("Executing MyRetailAppServiceImpl getProductDetails() method");
		return this.myRetailAppRepository.getProductDetails(id);
	}

	/**
	 * Method implementation to get product name based on product id, data should be
	 * fetched from provided url
	 * 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String retrieveProductName(String id) throws HttpClientErrorException {
		LOGGER.info("Executing MyRetailAppServiceImpl retrieveProductName() method");
		String title = "";
		try {

			URI url = new URI(
					"https://redsky.target.com/v1/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");
			ResponseEntity<String> responseBody = restTemplate.getForEntity(url, String.class);
			JSONObject noaaResponseObject = new JSONObject(new JSONTokener(responseBody.getBody()));
			title = noaaResponseObject.getJSONObject("product").getJSONObject("item")
					.getJSONObject("product_description").getString("title");

		} catch (Exception e) {
			LOGGER.debug("Error occured in retrieving Name from External API :: " + e);
		}
		return title;
	}

	/**
	 * Method implementation to get pricing information w.r.t to id and name, name
	 * should be fetched from external api
	 * 
	 * @param id
	 */
	@Override
	public Product readPricingInformation(String id) {
		LOGGER.info("Executing MyRetailAppServiceImpl readPricingInformation() method");
		String name = retrieveProductName(id);
		LOGGER.info("Name of the Product with Id = " + id + " is :: " + name);
		if ("".equals(name) || name == null) {
			return new Product();
		}
		return this.myRetailAppRepository.getPricingInformation(id, name);
	}

	/**
	 * Method implementation to update value in database for requested id
	 * 
	 * @param id
	 * @param value
	 */
	@Override
	public Product updateProductPrice(String id, Double value) {
		LOGGER.info("Executing MyRetailAppServiceImpl updateProductPrice() method");
		return this.myRetailAppRepository.updateProductPrice(id, value);
	}

}
