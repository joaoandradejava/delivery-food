package com.joaoandrade.deliveryfood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.joaoandrade.deliveryfood.core.jwt.JwtAuthorizationFilter;
import com.joaoandrade.deliveryfood.core.jwt.JwtUtil;
import com.joaoandrade.deliveryfood.domain.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private static final String[] PUBLIC_GET = { "/produtos/**" };
	private static final String[] PUBLIC_POST = { "/login", "/usuarios" };

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.GET, PUBLIC_GET).permitAll()
						.requestMatchers(HttpMethod.POST, PUBLIC_POST).permitAll().anyRequest().authenticated())
				.addFilterBefore(new JwtAuthorizationFilter(this.jwtUtil, this.usuarioRepository),
						UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
