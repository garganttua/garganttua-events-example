package com.garganttua.events.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.garganttua.events.context.sources.file.json.GGEventsContextJsonFileSource;
import com.garganttua.events.engine.GGEventsContextBuilder;
import com.garganttua.events.engine.GGEventsContextEngine;
import com.garganttua.events.spec.interfaces.IGGEventsContextBuilder;
import com.garganttua.events.spec.interfaces.IGGEventsContextSourceConfigurationRegistry;
import com.garganttua.events.spec.objects.GGEventsContextSourceConfiguration;
import com.garganttua.events.spec.objects.GGEventsContextSourceConfigurationRegistry;

public class SimpleGarganttuaEventsStandaloneAsset {
	
	public static void main(String[] args) {
				
		int threadPoolSize = 100;
		int maxThreadPoolSize = 200;
		long threadPoolKeepAliveTime = 100;
		TimeUnit threadPoolKeepAliveTimeUnit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
		ExecutorService executorService = new ThreadPoolExecutor(threadPoolSize/2, maxThreadPoolSize/2, threadPoolKeepAliveTime, threadPoolKeepAliveTimeUnit, workQueue);
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(threadPoolSize/2);;
		IGGEventsContextBuilder contextBuilder = new GGEventsContextBuilder();
		IGGEventsContextSourceConfigurationRegistry configRegistry = new GGEventsContextSourceConfigurationRegistry();
		String[] contextFiles = {"src/main/resources/context.json"};
		String[] pack = {"com.garganttua"};
		configRegistry.registerContextSourceConfiguration(new GGEventsContextSourceConfiguration(GGEventsContextJsonFileSource.SOURCE_NAME, contextFiles ));

		GGEventsContextEngine engine = new GGEventsContextEngine();
		engine.registerContextSourceConfiguratorRegistry(configRegistry);

		engine.init("0", contextBuilder, pack, executorService, scheduledExecutorService, "simple-asset", "0");
		engine.start();
	}
}
