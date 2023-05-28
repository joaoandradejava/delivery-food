package com.joaoandrade.deliveryfood.api.model;

import com.joaoandrade.deliveryfood.domain.model.Usuario;
import com.joaoandrade.deliveryfood.domain.model.enumeration.Perfil;

public class UsuarioAutenticadoModel {
	private Long id;
	private String nome;
	private String email;
	private String fotoPerfil;
	private String tokenJwt;
	private Boolean isAdmin;

	public UsuarioAutenticadoModel(Usuario usuario, String tokenJwt) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.fotoPerfil = usuario.getFotoPerfil();
		this.tokenJwt = tokenJwt;
		this.isAdmin = usuario.getPerfils().contains(Perfil.ADMINISTRADOR);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getTokenJwt() {
		return tokenJwt;
	}

	public void setTokenJwt(String tokenJwt) {
		this.tokenJwt = tokenJwt;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
