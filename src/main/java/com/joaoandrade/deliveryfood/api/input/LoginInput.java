package com.joaoandrade.deliveryfood.api.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginInput {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String senha;

	public LoginInput() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
