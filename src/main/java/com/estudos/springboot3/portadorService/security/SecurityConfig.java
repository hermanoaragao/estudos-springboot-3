package com.estudos.springboot3.portadorService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		
		http
		.authorizeHttpRequests((authorize) -> {
			authorize.requestMatchers("/tokens").permitAll();
			authorize.requestMatchers(AUTH_WHITELIST).permitAll();
			authorize.anyRequest().authenticated();
		})
		//.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())
		
		// faz a requisicao outh2ResourseServer do properties spring.security.oauth2.resourceserver.jwt.issuer-uri
		.oauth2ResourceServer(oauth2 -> oauth2.jwt( jwt -> jwt.jwtAuthenticationConverter(new JWTConverter()))
		);
		
		// aqui tá faltando configurar alguma coisa
		/*
		http.oauth2Client()
        .and()
        .oauth2ResourceServer()
        .jwt();
		*/
		return http.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		return JwtDecoders.fromIssuerLocation("http://localhost:8080/realms/Estudos_Spring");
	}
	
	
	
	/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// desabilitando o spring security
		//http.csrf(csrf -> csrf.disable());
		
		http
		.authorizeHttpRequests((authorize) -> {
			authorize.requestMatchers("/tokens").permitAll();
			authorize.anyRequest().authenticated();
		})
		.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())
		);
		
		http.oauth2Client()
        .and()
        .oauth2ResourceServer()
        .jwt();
		
		
		return http.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		return JwtDecoders.fromIssuerLocation("http://localhost:8080/realms/Estudos_Spring");
	}
	*/
	
	/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// desabilitando o spring security
		http
		.authorizeHttpRequests((authorize) -> {
			authorize.requestMatchers("/tokens").permitAll();
			authorize.anyRequest().authenticated();
		})
		.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())
		);
		
		.oauth2ResourceServer(oauth2 -> oauth2
				.jwt( jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())));
		
		return http.build();
	}
	*/
	
	/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.oauth2Client()
        .and()
        .oauth2ResourceServer()
        .jwt();

		http.sessionManagement()
    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeHttpRequests()
        .requestMatchers("/unauthenticated", "/oauth2/**", "/tokens/**").permitAll().anyRequest()
        .fullyAuthenticated()
        .and()
        .logout()
        .logoutSuccessUrl("https://keycloak.coffeeandit.com.br/auth/realms/master/protocol/openid-connect/logout?redirect_uri=http://localhost:8080/");
		
		return http.build();
	}
	*/

	
	
	

}
