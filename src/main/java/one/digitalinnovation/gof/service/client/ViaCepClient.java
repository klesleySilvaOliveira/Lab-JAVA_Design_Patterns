package one.digitalinnovation.gof.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.gof.model.dto.ViaCepResponseDTO;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

	@GetMapping("/{cep}/json/")
	ViaCepResponseDTO getCep(@PathVariable String cep);
}
