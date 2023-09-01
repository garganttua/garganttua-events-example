package com.garganttua.events.example;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.garganttua.events.spec.interfaces.IGGEventsEngine;

import jakarta.annotation.PostConstruct;

@Service
public class GGEventEngineService {
	
	@Inject
	private IGGEventsEngine engineService;
	
	@PostConstruct
	private void init() {
		this.engineService.start();
	}

}
