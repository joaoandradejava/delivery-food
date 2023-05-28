package com.joaoandrade.deliveryfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.deliveryfood.api.input.LoginInput;
import com.joaoandrade.deliveryfood.api.model.UsuarioAutenticadoModel;
import com.joaoandrade.deliveryfood.core.jwt.JwtUtil;
import com.joaoandrade.deliveryfood.domain.model.Usuario;
import com.joaoandrade.deliveryfood.domain.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping
	public UsuarioAutenticadoModel realizarLogin(@Valid @RequestBody LoginInput loginInput) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				loginInput.getEmail(), loginInput.getSenha());
		this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		Usuario usuario = this.usuarioRepository.findByEmail(loginInput.getEmail()).get();
		String tokenJwt = "Bearer " + this.jwtUtil.gerarTokenJwt(usuario.getId());

		return new UsuarioAutenticadoModel(usuario, tokenJwt);
	}

}
