package com.joaoandrade.deliveryfood.api.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaInput {

	@NotBlank
	@Size(max = 255)
	private String nome;

	public CategoriaInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
