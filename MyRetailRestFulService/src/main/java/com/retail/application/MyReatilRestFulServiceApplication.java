package com.retail.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.retail"})
public class MyReatilRestFulServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MyReatilRestFulServiceApplication.class, args);
	}
	
}
