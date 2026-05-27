package one.digitalinnovation.gof.model.entity;

import one.digitalinnovation.gof.model.entity.strategy.DeliveryStrategy;
import one.digitalinnovation.gof.model.entity.strategy.impl.EconomicDeliveryImpl;
import one.digitalinnovation.gof.model.entity.strategy.impl.FastDeliveryImpl;
import one.digitalinnovation.gof.model.entity.strategy.impl.NoDeliveryImpl;

public enum DeliveryType {

	ECONOMIC_DELIVERY(EconomicDeliveryImpl.getInstance()),
	FAST_DELIVERY(FastDeliveryImpl.getInstance()),
	NO_DELIVERY(NoDeliveryImpl.getInstance());
	
	final DeliveryStrategy deliveryStrategy;
	
	DeliveryType (DeliveryStrategy deliveryStrategy) {
		this.deliveryStrategy = deliveryStrategy;
	}

	public DeliveryStrategy getDeliveryStrategy() {
		return deliveryStrategy;
	}
	
}
