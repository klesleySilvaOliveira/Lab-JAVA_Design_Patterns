package one.digitalinnovation.gof.model.entity.strategy;

import one.digitalinnovation.gof.model.dto.DeliveryDataDTO;

public interface DeliveryStrategy {

	DeliveryDataDTO calculate(String uf);
}
