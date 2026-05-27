package one.digitalinnovation.gof.model.entity.strategy.impl;

import one.digitalinnovation.gof.model.dto.DeliveryDataDTO;
import one.digitalinnovation.gof.model.entity.strategy.DeliveryStrategy;

public class NoDeliveryImpl implements DeliveryStrategy {

	private static NoDeliveryImpl instance = new NoDeliveryImpl();

	private NoDeliveryImpl() {
		super();
	}

	public static NoDeliveryImpl getInstance() {
		return instance;
	}
	
	@Override
	public DeliveryDataDTO calculate(String uf) {
		
		return new DeliveryDataDTO(0.0, 0);
	}

}
