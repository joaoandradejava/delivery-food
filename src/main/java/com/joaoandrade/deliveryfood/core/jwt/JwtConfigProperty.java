package com.joaoandrade.deliveryfood.core.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt")
public class JwtConfigProperty {

	/**
	 * Senha do token jwt.
	 */
	private String senhaTokenJwt;

	/**
	 * Tempo de expiração do token jwt.
	 */
	private Long tempoExpiracaoTokenJwt;

	public JwtConfigProperty() {
	}

	public String getSenhaTokenJwt() {
		return senhaTokenJwt;
	}

	public void setSenhaTokenJwt(String senhaTokenJwt) {
		this.senhaTokenJwt = senhaTokenJwt;
	}

	public Long getTempoExpiracaoTokenJwt() {
		return tempoExpiracaoTokenJwt;
	}

	public void setTempoExpiracaoTokenJwt(Long tempoExpiracaoTokenJwt) {
		this.tempoExpiracaoTokenJwt = tempoExpiracaoTokenJwt;
	}

}
