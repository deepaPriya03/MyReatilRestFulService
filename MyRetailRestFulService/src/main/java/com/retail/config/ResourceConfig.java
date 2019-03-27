package com.retail.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ResourceConfig {

	@Autowired
	private Environment env;
	
	@Bean(name="dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.db.driverclass"));
        dataSource.setUrl(env.getProperty("spring.db.url"));
        dataSource.setUsername(env.getProperty("spring.db.username"));
        dataSource.setPassword(env.getProperty("spring.db.password"));
        return dataSource;
    }
	
	@Bean(name="jTemplate")
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name="restTemplate")
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
}
