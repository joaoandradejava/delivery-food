package com.joaoandrade.deliveryfood.core.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.joaoandrade.deliveryfood.core.security.UsuarioAutenticado;
import com.joaoandrade.deliveryfood.domain.model.Usuario;
import com.joaoandrade.deliveryfood.domain.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private JwtUtil jwtUtil;
	private UsuarioRepository usuarioRepository;

	public JwtAuthorizationFilter(JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
		super();
		this.jwtUtil = jwtUtil;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenJwt = request.getHeader("Authorization");

		if (StringUtils.hasLength(tokenJwt) && tokenJwt.startsWith("Bearer ")) {
			tokenJwt = tokenJwt.replaceAll("Bearer ", "");
			if (this.jwtUtil.isTokenJwtValido(tokenJwt)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = this
						.getAuthorization(this.jwtUtil.getSubject(tokenJwt));

				if (usernamePasswordAuthenticationToken != null) {
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}

			}
		}

		filterChain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthorization(Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

		if (usuario != null) {
			UsuarioAutenticado usuarioAutenticado = new UsuarioAutenticado(usuario);

			return new UsernamePasswordAuthenticationToken(usuarioAutenticado, null,
					usuarioAutenticado.getAuthorities());
		}

		return null;
	}

}
