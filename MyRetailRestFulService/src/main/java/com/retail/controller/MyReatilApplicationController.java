package com.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.retail.model.Product;
import com.retail.service.MyRetailAppService;

@RestController
public class MyReatilApplicationController {
	
	@Autowired
	private MyRetailAppService myRetailAppService;
	
	@RequestMapping(path="/getProductDetails/{id}", method=RequestMethod.GET)
	public Product getProductDetails(@PathVariable("id") String id){
		return this.myRetailAppService.getProductDetails(id);
	}
	
	@RequestMapping(path="/retrieveProductName", method=RequestMethod.GET)
	public @ResponseBody String retrieveProductName(@RequestParam String id){
		return this.myRetailAppService.retrieveProductName(id);
	}
	
	@RequestMapping(path="/readPricingInformation", method=RequestMethod.GET)
	public Product readPricingInformation(@RequestParam String id){
		return this.myRetailAppService.readPricingInformation(id);
	}
	
	@RequestMapping(path="/updateProductPrice/{id}", method=RequestMethod.PUT)
	public Product updateProductPrice(@PathVariable("id") String id, @RequestParam String value){
		return this.myRetailAppService.updateProductPrice(id, Double.parseDouble(value));
	}
	
}
