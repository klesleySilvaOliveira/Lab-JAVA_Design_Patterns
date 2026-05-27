package one.digitalinnovation.gof.model.entity.strategy.impl;

import java.util.Arrays;

import one.digitalinnovation.gof.model.dto.DeliveryDataDTO;
import one.digitalinnovation.gof.model.entity.strategy.DeliveryStrategy;

public class FastDeliveryImpl implements DeliveryStrategy {

	private static FastDeliveryImpl instance = new FastDeliveryImpl();

	private FastDeliveryImpl() {
		super();
	}

	public static FastDeliveryImpl getInstance() {
		return instance;
	}

	@Override
	public DeliveryDataDTO calculate(String uf) {

		if (uf.toUpperCase().equals("MG")) {
			return new DeliveryDataDTO(19.9, 1);
		} else if (Arrays.asList("SP", "RJ", "ES").contains(uf.toUpperCase())) {
			return new DeliveryDataDTO(29.9, 2);
		} else
			return new DeliveryDataDTO(39.9, 4);
	}

}
