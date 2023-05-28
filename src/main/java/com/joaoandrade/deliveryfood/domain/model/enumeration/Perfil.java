package com.joaoandrade.deliveryfood.domain.model.enumeration;

public enum Perfil {
	COMUM("ROLE_COMUM"), ADMINISTRADOR("ROLE_ADMINISTRADOR");

	private String descricao;

	private Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
