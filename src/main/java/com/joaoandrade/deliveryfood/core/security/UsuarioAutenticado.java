package com.joaoandrade.deliveryfood.core.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.joaoandrade.deliveryfood.domain.model.Usuario;
import com.joaoandrade.deliveryfood.domain.model.enumeration.Perfil;

public class UsuarioAutenticado implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final Long id;
	private final String email;
	private final String senha;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean isAdmin;

	public UsuarioAutenticado(Usuario usuario) {
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.authorities = usuario.getPerfils().stream()
				.map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao())).collect(Collectors.toList());
		this.isAdmin = usuario.getPerfils().contains(Perfil.ADMINISTRADOR);
	}

	public Long getId() {
		return id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
