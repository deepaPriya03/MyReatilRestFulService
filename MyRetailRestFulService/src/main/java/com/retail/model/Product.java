package com.retail.model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private PriceDetail current_price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PriceDetail getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(PriceDetail current_price) {
		this.current_price = current_price;
	}

}
