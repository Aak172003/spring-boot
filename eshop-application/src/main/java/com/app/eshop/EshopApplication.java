package com.app.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// All the autoconfiguration things hidden inside this annotation
// Also this tells , consider this project as spring boot project
// It enable Autoconfiguration like -> DispatcherServlet , AppConfig , EnableWebMVC, ComponentScan
@SpringBootApplication
public class EshopApplication {

	public static void main(String[] args) {
		System.out.println("Application is running");
		SpringApplication.run(EshopApplication.class, args);
	}

}
