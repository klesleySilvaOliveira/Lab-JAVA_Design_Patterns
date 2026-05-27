package one.digitalinnovation.gof.model.entity.strategy.impl;

import java.util.Arrays;

import one.digitalinnovation.gof.model.dto.DeliveryDataDTO;
import one.digitalinnovation.gof.model.entity.strategy.DeliveryStrategy;

public class EconomicDeliveryImpl implements DeliveryStrategy {

	private static EconomicDeliveryImpl instance = new EconomicDeliveryImpl();
	
	private EconomicDeliveryImpl() {
		super();
	}

	public static EconomicDeliveryImpl getInstance() {
		return instance;
	}

	@Override
	public DeliveryDataDTO calculate(String uf) {
		
		if (uf.toUpperCase().equals("MG")) {
			return new DeliveryDataDTO(9.9, 3);
		}
		else if (Arrays.asList("SP","RJ","ES").contains(uf.toUpperCase())) {
			return new DeliveryDataDTO(14.9, 5);
		}
		else
			return new DeliveryDataDTO(24.9, 8);
	}

}
