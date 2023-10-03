package com.thanhle.AirlinesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.thanhle.AirlinesApp"})
public class AirlinesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlinesAppApplication.class, args);
	}

}
