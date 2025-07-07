package com.example.sie.taco_cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//SpringBoot application
//@SpringBootApplication annotation clearly signifies that this is a Spring Boot application

//@SpringBootApplication is a composite annotation that combines the following
//three annotations:
//@SpringBootConfiguration—Designates this class as a configuration class.
//@EnableAutoConfiguration—Enables Spring Boot automatic configuration.
//@ComponentScan—Enables component scanning.
//application context.
@SpringBootApplication
public class TacoCloudApplication {

	//Runs the application
	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
