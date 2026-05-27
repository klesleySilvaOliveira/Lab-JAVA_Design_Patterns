package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.dto.ClientResponseDTO;
import one.digitalinnovation.gof.model.dto.DeliveryDataDTO;
import one.digitalinnovation.gof.service.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@GetMapping(value = "/{data}/{uf}")
	public ResponseEntity<DeliveryDataDTO> getClients(@PathVariable("data") String delivery, @PathVariable("uf") String uf){
		return ResponseEntity.ok(service.consult(delivery, uf));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id){
		return ResponseEntity.ok(service.searchById(id));
	}
	
	@PostMapping
	public ResponseEntity<ClientResponseDTO> insert(){
		return null;
	}
}
