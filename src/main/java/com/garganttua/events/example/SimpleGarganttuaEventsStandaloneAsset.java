package com.garganttua.events.example;

import com.garganttua.events.engine.GGEventsBuilder;
import com.garganttua.events.spec.exceptions.GGEventsException;

public class SimpleGarganttuaEventsStandaloneAsset {
	
	public static void main(String[] args) throws GGEventsException {
		GGEventsBuilder.builder("0").source("json-file", "1.0", "./src/main/resources/context.json").build().start();
	}
}
