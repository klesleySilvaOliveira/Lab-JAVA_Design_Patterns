package one.digitalinnovation.gof.model.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String cep;
	private String logradouro;
	private String bairro;
	private String complemento;
	private String cidade;
	private String uf;
	private String tipoEntrega;
	private Double valorEntrega;
	private Integer prazoDias;
	
	public Client() {
		super();
	}
	
	public Client(Long id, String nome, String cep, String logradouro, String bairro, String complemento,
			String cidade, String uf, String tipoEntrega, Double valorEntrega, Integer prazoDias) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

	public Double getValorEntrega() {
		return valorEntrega;
	}

	public void setValorEntrega(Double valorEntrega) {
		this.valorEntrega = valorEntrega;
	}

	public Integer getPrazoDias() {
		return prazoDias;
	}

	public void setPrazoDias(Integer prazoDias) {
		this.prazoDias = prazoDias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nome=" + nome + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro="
				+ bairro + ", complemento=" + complemento + ", cidade=" + cidade + ", uf=" + uf + ", tipoEntrega="
				+ tipoEntrega + ", valorEntrega=" + valorEntrega + ", prazoDias=" + prazoDias + "]";
	}
	
}
