package com.joaoandrade.deliveryfood.core.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Autowired
	private JwtConfigProperty jwtConfigProperty;

	public String gerarTokenJwt(Long id) {
		return Jwts.builder().setSubject(id.toString())
				.setExpiration(
						new Date(System.currentTimeMillis() + this.jwtConfigProperty.getTempoExpiracaoTokenJwt()))
				.signWith(SignatureAlgorithm.HS512, this.jwtConfigProperty.getSenhaTokenJwt().getBytes()).compact();
	}

	public Long getSubject(String tokenJwt) {
		Claims claims = this.getClaims(tokenJwt);

		return claims != null ? Long.parseLong(claims.getSubject()) : null;
	}

	public boolean isTokenJwtValido(String tokenJwt) {
		Claims claims = this.getClaims(tokenJwt);

		if (claims != null) {
			String subject = claims.getSubject();
			Date now = new Date();
			Date expiration = claims.getExpiration();

			if (StringUtils.hasLength(subject) && expiration != null && now.before(expiration)) {
				return true;
			}
		}

		return false;
	}

	private Claims getClaims(String tokenJwt) {
		try {
			return Jwts.parser().setSigningKey(this.jwtConfigProperty.getSenhaTokenJwt().getBytes())
					.parseClaimsJws(tokenJwt).getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
