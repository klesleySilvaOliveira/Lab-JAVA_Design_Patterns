package one.digitalinnovation.gof.service;

import org.apache.tomcat.util.http.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.dto.ViaCepResponseDTO;
import one.digitalinnovation.gof.service.client.ViaCepClient;

@Service
public class CepService {
	
	@Autowired
	private ViaCepClient viaCepClient;
	
	public ViaCepResponseDTO getCep(String cep) {
        validateCep(cep);

        ViaCepResponseDTO response = viaCepClient.getCep(cep);

        return response;
    }

    private void validateCep(String cep) {
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new InvalidParameterException("The CEP value must have exactly 8 numeric digits");
        }
    }
}
