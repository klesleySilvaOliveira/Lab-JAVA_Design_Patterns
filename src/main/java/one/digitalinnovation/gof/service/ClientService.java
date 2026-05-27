package one.digitalinnovation.gof.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import one.digitalinnovation.gof.model.dto.ClientRequestDTO;
import one.digitalinnovation.gof.model.dto.ClientResponseDTO;
import one.digitalinnovation.gof.model.dto.DeliveryDataDTO;
import one.digitalinnovation.gof.model.dto.ViaCepRequestDTO;
import one.digitalinnovation.gof.model.entity.Client;
import one.digitalinnovation.gof.model.entity.DeliveryType;
import one.digitalinnovation.gof.model.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ViaCepService viaCepService;
	
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
	
	@Transactional
	public ClientResponseDTO insert(ClientRequestDTO dto) {
		
		Client client = new Client();
		copyRequestDtoToClient(dto, client);
		client = repository.save(client);
		
		return new ClientResponseDTO(client);
	}

	private void copyRequestDtoToClient(ClientRequestDTO dto, Client client) {
		
		DeliveryType type = DeliveryType.valueOf(dto.getTipoEntrega().toUpperCase());		
		ViaCepRequestDTO viaCep = viaCepService.getCep(dto.getCep());
		DeliveryDataDTO dataDto = type.getDeliveryStrategy().calculate(viaCep.getUf());
		
		client.setNome(dto.getNome());
		client.setCep(dto.getCep());
		client.setLogradouro(viaCep.getLogradouro());
		client.setBairro(viaCep.getBairro());
		client.setComplemento(viaCep.getComplemento());
		client.setCidade(viaCep.getLocalidade());
		client.setUf(viaCep.getUf());
		client.setTipoEntrega(type);
		client.setValorEntrega(dataDto.getDeliveryValue());
		client.setPrazoDias(dataDto.getDays());
		
	}
}
