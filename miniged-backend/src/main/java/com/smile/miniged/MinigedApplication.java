package com.smile.miniged;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MinigedApplication{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MinigedApplication.class);
	public static void main(String[] args) {
		logger.info("||||| Beginning of the execution of the main class");
		SpringApplication.run(MinigedApplication.class, args);
		logger.info("||||| End of the execution of the main class");
	}
}