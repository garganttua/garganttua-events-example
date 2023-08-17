package com.garganttua.events.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.garganttua"})
@SpringBootApplication
public class SimpleGarganttuaSpringEventAsset {
	
	public static void main(String args[]) {
		SpringApplication.run(SimpleGarganttuaSpringEventAsset.class, args);
	}
}
