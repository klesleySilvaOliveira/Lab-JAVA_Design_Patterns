package one.digitalinnovation.gof.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(readOnly = true)
	public ClientResponseDTO searchById(Long id) {
		return new ClientResponseDTO(repository.findById(id).get());
	}
	
	@Transactional(readOnly = true)
	public Page<ClientResponseDTO> searchAll(Pageable pageable) {
		return repository.findAll(pageable).map(x -> new ClientResponseDTO(x));
	} 
}
