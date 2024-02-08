package com.estudos.springboot3.portadorService.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JWTConverter implements Converter<Jwt, AbstractAuthenticationToken>{

	
	@Override
	public AbstractAuthenticationToken convert(Jwt token) {
		Map<String, Collection<String>> realmAcess = token.getClaim("realm_access");
		Collection<String> roles = realmAcess.get("roles");
		var grants = roles
				.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+role)).toList();
		return new JwtAuthenticationToken(token, grants);
	}

}
