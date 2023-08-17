package com.garganttua.events.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.garganttua.events.context.sources.file.json.GGEventsContextJsonFileSource;
import com.garganttua.events.spec.interfaces.IGGEventsCoreEventHandler;
import com.garganttua.events.spec.interfaces.IGGEventsObjectRegistry;
import com.garganttua.events.spec.objects.GGEventsContextSourceConfiguration;
import com.garganttua.events.spring.GGEventsCoreEventHandler;
import com.garganttua.events.spring.GGEventsSpringBeanRegistry;

@Configuration
public class GarganttuaEventsSpringConfiguration {
	
	@Bean
	public GGEventsContextSourceConfiguration fakeConfiguration() {
		String[] contextFiles = {"src/main/resources/context.json"};
		return new GGEventsContextSourceConfiguration(GGEventsContextJsonFileSource.SOURCE_NAME, contextFiles);
	}	
	
	@Bean 
	public String[] packages() {
		String[] packages = {"com.garganttua"};
		return packages;
	}
	
	@Bean 
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(10);
	}
	
	@Bean
	public ScheduledExecutorService scheduledExecutorService() {
		return Executors.newScheduledThreadPool(10);
	}
	
	@Inject 
	private ApplicationContext context;
	
	@Bean
	public IGGEventsCoreEventHandler eventHandler() {
		return new GGEventsCoreEventHandler(this.context);
	}	
	
	@Bean
	public IGGEventsObjectRegistry beanObjectsRegistry() {
		return new GGEventsSpringBeanRegistry(this.context);
	}
	
}
