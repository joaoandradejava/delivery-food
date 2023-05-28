package com.joaoandrade.deliveryfood.domain.filter;

import java.util.HashSet;
import java.util.Set;

public class ProdutoFilter {
	private String nome;
	private Boolean isDisponivel = Boolean.FALSE;
	private Set<Long> categoriaIds = new HashSet<Long>();

	public ProdutoFilter() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getIsDisponivel() {
		return isDisponivel;
	}

	public void setIsDisponivel(Boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}

	public Set<Long> getCategoriaIds() {
		return categoriaIds;
	}

	public void setCategoriaIds(Set<Long> categoriaIds) {
		this.categoriaIds = categoriaIds;
	}

}
