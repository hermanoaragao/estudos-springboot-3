package com.estudos.springboot3.portadorService.controller.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tokens")
public class TokenController {

	// variavel que pega o campo no application.properties
	@Value("${url.keycloak}")
	private String urlKeyCloak;
	
	/**
	 * Faz a geração de token batendo no keycloak
	 * @param user
	 * @return
	 * 
	 // Exemplo do body da requisicao: todos os campos passados no body, precisam estar configurados no keycloak
	 {
	    "client_id": "app_portadorService",
	    "username": "user_portador_service",
	    "password": "portador",
	    "grant_type": "password"
	 }
	 */
	@PostMapping
	public ResponseEntity<String> token(@RequestBody User user) {
		
		HttpHeaders headers = new HttpHeaders();
		RestTemplate rt = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
		formData.add("client_id", user.client_id);
		formData.add("username", user.username);
		formData.add("password", user.password);
		formData.add("grant_type", user.grant_type);
		
		HttpEntity<MultiValueMap<String, String> > entity = new HttpEntity<MultiValueMap<String,String>>(formData, headers);
		
		// chamada no keycloak
		ResponseEntity<String> retorno = rt.postForEntity(urlKeyCloak, entity, String.class);
		return retorno;
	}
	
	// classe para mapear o request recebido no token
	public record User(String client_id, String username, String password, String grant_type) {}
}
