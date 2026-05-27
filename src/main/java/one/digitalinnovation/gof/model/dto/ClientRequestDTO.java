package one.digitalinnovation.gof.model.dto;

public class ClientRequestDTO {

	private String nome;
	private String cep;
	private String tipoEntrega;
	
	public ClientRequestDTO() {
		super();
	}
	
	public ClientRequestDTO(String nome, String cep, String tipoEntrega) {
		super();
		this.nome = nome;
		this.cep = cep;
		this.tipoEntrega = tipoEntrega;
	}

	public String getNome() {
		return nome;
	}

	public String getCep() {
		return cep;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	@Override
	public String toString() {
		return "ClientRequestDTO [nome=" + nome + ", cep=" + cep + ", tipoEntrega=" + tipoEntrega + "]";
	}
	
}
