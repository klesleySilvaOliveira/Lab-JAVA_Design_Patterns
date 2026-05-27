package one.digitalinnovation.gof.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import one.digitalinnovation.gof.model.dto.ClientRequestDTO;
import one.digitalinnovation.gof.model.dto.ClientResponseDTO;
import one.digitalinnovation.gof.service.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id){
		return ResponseEntity.ok(service.searchById(id));
	}
	
	@GetMapping
	public ResponseEntity<Page<ClientResponseDTO>> getClients(Pageable pageable){
		return ResponseEntity.ok(service.searchAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<ClientResponseDTO> insert(@RequestBody ClientRequestDTO dto){
		System.out.println(dto);
		ClientResponseDTO responseDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(responseDto.getId()).toUri();
		return ResponseEntity.created(uri).body(responseDto);
	}
}
