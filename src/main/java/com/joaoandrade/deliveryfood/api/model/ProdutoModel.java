package com.joaoandrade.deliveryfood.api.model;

import java.math.BigDecimal;

public class ProdutoModel {
	private String id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Boolean isDisponivel;
	private String fotoUrl;

	public ProdutoModel() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Boolean getIsDisponivel() {
		return isDisponivel;
	}

	public void setIsDisponivel(Boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

}
