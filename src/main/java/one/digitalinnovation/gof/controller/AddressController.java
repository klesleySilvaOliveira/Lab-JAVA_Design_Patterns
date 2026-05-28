package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.dto.ViaCepResponseDTO;
import one.digitalinnovation.gof.service.CepService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

	@Autowired
	private CepService service;

	@GetMapping(value = "/{cep}")
	public ResponseEntity<ViaCepResponseDTO> getAddressByCep(@PathVariable String cep) {
		return ResponseEntity.ok(service.getCep(cep));
	}
}
