package com.regpeople.apiregpeople;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.regpeople.apiregpeople", "controller", "service"})
@SpringBootApplication
public class RegPeopleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegPeopleApplication.class, args);
	}

}
