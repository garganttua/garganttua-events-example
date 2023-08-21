package com.garganttua.events.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garganttua.events.context.GGEventsContextSubscription;
import com.garganttua.events.spec.annotations.GGEventsConnector;
import com.garganttua.events.spec.enums.GGEventsMediaType;
import com.garganttua.events.spec.exceptions.GGEventsConnectorException;
import com.garganttua.events.spec.exceptions.GGEventsCoreException;
import com.garganttua.events.spec.exceptions.GGEventsCoreProcessingException;
import com.garganttua.events.spec.interfaces.IGGEventsConnector;
import com.garganttua.events.spec.interfaces.IGGEventsMessageHandler;
import com.garganttua.events.spec.interfaces.IGGEventsObjectRegistryHub;
import com.garganttua.events.spec.objects.GGEventsContextObjDescriptor;
import com.garganttua.events.spec.objects.GGEventsExchange;
import com.garganttua.events.spec.objects.GGEventsJourneyStep;
import com.garganttua.events.spec.objects.GGEventsMessage;

import ch.qos.logback.core.testUtil.RandomUtil;
import lombok.Getter;
import lombok.Setter;

@GGEventsConnector(type = "int-generator", version = "1.0.0")
public class IntGeneratorConnector implements IGGEventsConnector {

	@Getter
	@Setter
	private String name;
	private ExecutorService poolExecutor;
	private IGGEventsMessageHandler messageHandler;
	private String tenantId;

	@Override
	public void handle(GGEventsExchange exchange) throws GGEventsCoreProcessingException, GGEventsCoreException {
		// TODO Auto-generated method stub
	}

	@Override
	public String getType() {
		return "int-generator";
	}

	@Override
	public String getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConfiguration(String configuration, String tenantId, String clusterId, String assetId,
			IGGEventsObjectRegistryHub objectRegistries) throws GGEventsCoreException {
		this.tenantId = tenantId;
	}

	@Override
	public void applyConfiguration() throws GGEventsCoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public GGEventsContextObjDescriptor getDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPoolExecutor(ExecutorService poolExecutor) {
		this.poolExecutor = poolExecutor;
	}

	@Override
	public void start() throws GGEventsConnectorException {
		this.poolExecutor.execute(new Thread() {
			public void run() {
				ObjectMapper mapper = new ObjectMapper();
				while(true) {
					
					
					System.out.println("Sending integer");
					
					GGEventsMessage message = new GGEventsMessage(new HashMap<String, String>(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), new ArrayList<GGEventsJourneyStep>(), tenantId, String.valueOf(RandomUtil.getPositiveInt()).getBytes(), GGEventsMediaType.TEXT_PLAIN.toString(), null, null, "1.0");
					byte[] __bytes__ = null;
					try {
						__bytes__ = mapper.writeValueAsString(message).getBytes();
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					GGEventsExchange exchange = GGEventsExchange.emptyExchange(getName(), "/integers", "b9a68675-20a6-494f-b921-356e3a84dfad", __bytes__);
					
					Thread t = new Thread() {
						
						public void run(){
							try {
								messageHandler.handle(exchange);
							} catch (GGEventsCoreException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					};
					poolExecutor.execute(t);
					
					
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	@Override
	public void stop() throws GGEventsConnectorException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerConsumer(GGEventsContextSubscription subscription, IGGEventsMessageHandler messageHandler,
			String tenantId, String clusterId, String assetId) {
		this.messageHandler = messageHandler;

	}

	@Override
	public void registerProducer(GGEventsContextSubscription subscription, String tenantId, String clusterId,
			String assetId) {
		// TODO Auto-generated method stub

	}

}
