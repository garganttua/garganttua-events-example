package com.garganttua.events.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.garganttua.events.context.json.sources.file.GGEventsContextJsonFileSource;
import com.garganttua.events.spec.interfaces.IGGEventsContextSource;

@Configuration
public class GarganttuaEventsSpringConfiguration {
	
	@Bean
	public IGGEventsContextSource configuration() {
		return new GGEventsContextJsonFileSource("./src/main/resources/context.json");
	}	
	
	@Bean 
	@Qualifier(value = "packages")
	public String packages() {
		return "com.garganttua";
	}
	
}
