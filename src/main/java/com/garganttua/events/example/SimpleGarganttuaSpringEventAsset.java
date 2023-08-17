package com.garganttua.events.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.garganttua.events.spec.objects.GGEventsContextSourceConfiguration;

@Configuration
@ComponentScan({"com.garganttua"})
@SpringBootApplication
public class SimpleGarganttuaSpringEventAsset {
	
	
	public static void main(String args[]) {
		SpringApplication.run(SimpleGarganttuaSpringEventAsset.class, args);
	}
}
