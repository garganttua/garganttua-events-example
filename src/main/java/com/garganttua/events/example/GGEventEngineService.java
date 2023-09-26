package com.garganttua.events.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garganttua.events.spec.exceptions.GGEventsException;
import com.garganttua.events.spring.IGGEventsEngineSpringBean;

import jakarta.annotation.PostConstruct;

@Service
public class GGEventEngineService {
	
	@Autowired
	private IGGEventsEngineSpringBean engineService;
	
	@PostConstruct
	private void init() throws GGEventsException {
		this.engineService.init();
		this.engineService.start();
	}

}
