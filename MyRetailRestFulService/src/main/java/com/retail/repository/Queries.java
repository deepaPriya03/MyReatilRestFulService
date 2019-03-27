package com.retail.repository;

public class Queries {
	
	public static final String PRODUCT_DETAILS_QUERY = "SELECT * FROM TargetExercise.Products WHERE id=?";
	public static final String PRICE_DETAILS_QUERY = "SELECT * FROM TargetExercise.Products WHERE id=? AND name=?";
	public static final String UPDATE_PRODUCT_PRICE_QUERY = "UPDATE TargetExercise.Products SET value=? WHERE id=?";

}
