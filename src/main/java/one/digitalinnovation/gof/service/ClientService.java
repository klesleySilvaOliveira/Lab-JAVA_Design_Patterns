package one.digitalinnovation.gof.service;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import one.digitalinnovation.gof.model.dto.ClientRequestDTO;
import one.digitalinnovation.gof.model.dto.ClientResponseDTO;
import one.digitalinnovation.gof.model.dto.DeliveryDataDTO;
import one.digitalinnovation.gof.model.dto.ViaCepResponseDTO;
import one.digitalinnovation.gof.model.entity.Client;
import one.digitalinnovation.gof.model.entity.DeliveryType;
import one.digitalinnovation.gof.model.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private CepService viaCepService;

	@Transactional(readOnly = true)
	public ClientResponseDTO searchById(Long id) {
		Client client = repository.findById(id)
				.orElseThrow(() -> new OpenApiResourceNotFoundException("Client not found"));

		return new ClientResponseDTO(client);
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

	@Transactional
	public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
		
		try {
			Client client = repository.getReferenceById(id);
			copyRequestDtoToClient(dto, client);
			client = repository.save(client);
			return new ClientResponseDTO(client);
		}
		catch (EntityNotFoundException e) {
			throw new OpenApiResourceNotFoundException("Resource not found!");
		}	
		
	}
	
	@Transactional
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new OpenApiResourceNotFoundException("Resource not found!");
		}
		try {
	        	repository.deleteById(id);    		
		}
	    	catch (DataIntegrityViolationException e) {
	        	e.printStackTrace();
	   	}
	}

	private void copyRequestDtoToClient(ClientRequestDTO dto, Client client) {

		DeliveryType type = DeliveryType.valueOf(dto.getTipoEntrega().toUpperCase());
		ViaCepResponseDTO viaCep = viaCepService.getCep(dto.getCep());
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
