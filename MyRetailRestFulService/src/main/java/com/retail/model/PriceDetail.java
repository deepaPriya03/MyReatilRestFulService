package com.retail.model;

import java.io.Serializable;

public class PriceDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	private Double value;
	private String currency_code;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

}