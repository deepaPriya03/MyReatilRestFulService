package com.retail.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public abstract class DataMapper<T> implements RowMapper<T>{
	
	@Override
	public abstract T mapRow(ResultSet rs, int rowNum) throws SQLException; 

}
