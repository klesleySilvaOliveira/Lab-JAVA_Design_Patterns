package one.digitalinnovation.gof.controller;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.dto.ViaCepRequestDTO;
import one.digitalinnovation.gof.service.ViaCepService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

	@Autowired
	private ViaCepService service;

	@GetMapping(value = "/{cep}")
	public ResponseEntity<ViaCepRequestDTO> getAddressByCep(@PathVariable String cep) {
		if (cep.matches("\\d+") && cep.length() == 8) {
			return ResponseEntity.ok(service.getCep(cep));
		} else {
			throw new InvalidParameterException("The CEP value must be valid");
		}

	}
}
