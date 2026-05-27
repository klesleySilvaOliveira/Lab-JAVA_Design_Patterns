package one.digitalinnovation.gof.model.dto;

import one.digitalinnovation.gof.model.entity.DeliveryType;

public class ClientRequestDTO {

	private String nome;
	private String cep;
	private DeliveryType tipoEntrega;
	
	public ClientRequestDTO() {
		super();
	}
	
	public ClientRequestDTO(String nome, String cep, String tipoEntrega) {
		super();
		this.nome = nome;
		this.cep = cep;
		this.tipoEntrega = DeliveryType.valueOf(tipoEntrega);
	}

	public String getNome() {
		return nome;
	}

	public String getCep() {
		return cep;
	}

	public DeliveryType getTipoEntrega() {
		return tipoEntrega;
	}
	
}
