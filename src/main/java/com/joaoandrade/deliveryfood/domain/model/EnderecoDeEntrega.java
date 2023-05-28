package com.joaoandrade.deliveryfood.domain.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_endereco_de_entrega", sequenceName = "seq_endereco_de_entrega", initialValue = 1, allocationSize = 1)
public class EnderecoDeEntrega {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco_de_entrega")
	private Long id;

	@Column(nullable = false)
	private String endereco;

	@Column(nullable = false)
	private String complemento;

	public EnderecoDeEntrega() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
		EnderecoDeEntrega other = (EnderecoDeEntrega) obj;
		return Objects.equals(id, other.id);
	}

}
