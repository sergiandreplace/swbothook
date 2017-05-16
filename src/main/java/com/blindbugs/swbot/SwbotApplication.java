package com.blindbugs.swbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.blindbugs.swbot.application"})
@SpringBootApplication
@EnableCaching

public class SwbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwbotApplication.class, args);
	}
}
