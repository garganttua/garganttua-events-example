package com.garganttua.events.example;

import com.garganttua.events.engine.GGEventsBuilder;

public class SimpleGarganttuaEventsStandaloneAsset {
	
	public static void main(String[] args) {
		GGEventsBuilder.builder("0").source("json-file", "1.0", "./src/main/resources/context.json").build().start();
	}
}
