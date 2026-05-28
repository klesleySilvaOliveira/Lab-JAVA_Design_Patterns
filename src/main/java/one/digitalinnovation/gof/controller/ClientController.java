package one.digitalinnovation.gof.controller;

import java.net.URI;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import one.digitalinnovation.gof.model.dto.ClientRequestDTO;
import one.digitalinnovation.gof.model.dto.ClientResponseDTO;
import one.digitalinnovation.gof.service.ClientService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Clients", description = "Endpoints para cadastro, consulta, atualização e exclusão de clientes")
@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;

	@Operation(
		    summary = "Busca cliente por ID",
		    description = "Retorna um cliente correspondente ao ID enviado."
		)
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id){
		return ResponseEntity.ok(service.searchById(id));
	}
	
	@Operation(
		    summary = "Lista clientes cadastrados",
		    description = "Retorna uma lista paginada de clientes, permitindo paginação e ordenação por campos como id, nome, uf e tipoEntrega."
		)
	@GetMapping
	public ResponseEntity<Page<ClientResponseDTO>> getClients(
			@ParameterObject
	        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
	        Pageable pageable){
		return ResponseEntity.ok(service.searchAll(pageable));
	}
	
	@Operation(
		    summary = "Cadastra um novo cliente",
		    description = "Cadastra um cliente, consulta automaticamente o endereço pelo CEP usando ViaCEP e calcula o valor/prazo de entrega conforme o tipo informado."
		)
	@PostMapping
	public ResponseEntity<ClientResponseDTO> insert(@RequestBody ClientRequestDTO dto){
		ClientResponseDTO responseDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(responseDto.getId()).toUri();
		return ResponseEntity.created(uri).body(responseDto);
	}
	
	@Operation(
		    summary = "Atualiza os dados de um cliente",
		    description = "Acessa o cliente correspondente ao ID e atualiza seus dados com os valores enviados."
		)
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @RequestBody ClientRequestDTO dto){
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@Operation(
		    summary = "Deleta os dados de um cliente",
		    description = "Deleta os dados do cliente correspondente ao ID enviado."
		)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
