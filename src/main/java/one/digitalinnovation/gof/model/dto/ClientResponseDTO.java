package one.digitalinnovation.gof.model.dto;

import one.digitalinnovation.gof.model.entity.Client;
import one.digitalinnovation.gof.model.entity.DeliveryType;

public class ClientResponseDTO {

	private Long id;
	private String nome;
	private String cep;
	private String logradouro;
	private String bairro;
	private String complemento;
	private String cidade;
	private String uf;
	private DeliveryType tipoEntrega;
	private Double valorEntrega;
	private Integer prazoDias;
	
	
	public ClientResponseDTO() {
		super();
	}


	public ClientResponseDTO(Long id, String nome, String cep, String logradouro, String bairro, String complemento,
			String cidade, String uf, DeliveryType tipoEntrega, Double valorEntrega, Integer prazoDias) {
		super();
		this.id = id;
		this.nome = nome;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
		this.tipoEntrega = tipoEntrega;
		this.valorEntrega = valorEntrega;
		this.prazoDias = prazoDias;
	}
	
	public ClientResponseDTO(Client client) {
		super();
		this.id = client.getId();
		this.nome = client.getNome();
		this.cep = client.getCep();
		this.logradouro = client.getLogradouro();
		this.bairro = client.getBairro();
		this.complemento = client.getComplemento();
		this.cidade = client.getCidade();
		this.uf = client.getUf();
		this.tipoEntrega = client.getTipoEntrega();
		this.valorEntrega = client.getValorEntrega();
		this.prazoDias = client.getPrazoDias();
	}


	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public String getCep() {
		return cep;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public String getBairro() {
		return bairro;
	}


	public String getComplemento() {
		return complemento;
	}


	public String getCidade() {
		return cidade;
	}


	public String getUf() {
		return uf;
	}


	public DeliveryType getTipoEntrega() {
		return tipoEntrega;
	}


	public Double getValorEntrega() {
		return valorEntrega;
	}


	public Integer getPrazoDias() {
		return prazoDias;
	}
	
}
