package com.joaoandrade.deliveryfood.core.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joaoandrade.deliveryfood.domain.model.Usuario;
import com.joaoandrade.deliveryfood.domain.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = this.repository.findByEmail(username);

		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}

		return new UsuarioAutenticado(usuario.get());
	}

}
