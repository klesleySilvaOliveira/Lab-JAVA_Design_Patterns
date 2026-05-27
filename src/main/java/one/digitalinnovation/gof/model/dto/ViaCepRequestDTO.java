package one.digitalinnovation.gof.model.dto;

public class ViaCepRequestDTO {

	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	
	public ViaCepRequestDTO() {
		super();
	}
	
	public ViaCepRequestDTO(String logradouro, String complemento, String bairro, String localidade, String uf) {
		super();
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public String getUf() {
		return uf;
	}
	
	
}
