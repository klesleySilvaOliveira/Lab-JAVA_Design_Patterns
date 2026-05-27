package one.digitalinnovation.gof.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.dto.ClientResponseDTO;
import one.digitalinnovation.gof.model.dto.DeliveryDataDTO;
import one.digitalinnovation.gof.model.entity.DeliveryType;
import one.digitalinnovation.gof.model.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public DeliveryDataDTO consult(String deliveryType, String uf) {
		DeliveryType type = DeliveryType.valueOf(deliveryType.toUpperCase());
		
		return type.getDeliveryStrategy().calculate(uf);
	}
	
	public ClientResponseDTO searchById(Long id) {
		return new ClientResponseDTO(repository.findById(id).get());
	}
}
