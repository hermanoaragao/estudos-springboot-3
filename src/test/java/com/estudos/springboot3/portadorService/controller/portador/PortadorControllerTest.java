package com.estudos.springboot3.portadorService.controller.portador;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.estudos.springboot3.portadorService.entity.endereco.Endereco;
import com.estudos.springboot3.portadorService.entity.portador.Portador;
import com.estudos.springboot3.portadorService.service.endereco.EnderecoServiceImpl;
import com.estudos.springboot3.portadorService.service.portador.PortadorServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class PortadorControllerTest {

	public static final Long ID_PORTADOR = 100L;
    public static final String NOME_PORTADOR = "Junit - Portador Fulano";
    public static final String CPF_CNPJ = "Junit - 00812926439";
    public static final String TELEFONE = "Junit - 996745900";
    
    
    @InjectMocks
	PortadorController portadorController;
    
    @Mock
	PortadorServiceImpl portadorService;
    
    @Mock
    EnderecoServiceImpl enderecoService;
    
    private MockMvc mockMvc;
    
    private String jsonPortador;
	
	private String url;
	
	private Portador portador;
	
	private Endereco endereco;
	
	private final ObjectMapper objMapper = new ObjectMapper();
	
	
	@BeforeEach
	public void setup() throws JsonProcessingException {
		this.mockMvc = MockMvcBuilders.standaloneSetup(portadorController).alwaysDo(MockMvcResultHandlers.print()).build();
		this.url = "/portadores";
		
		startPortador();
		
		// preenche o json com um dto de portador
		jsonPortador = objMapper.writeValueAsString(portador);
	}
	
	private void startPortador(){
		portador = new Portador.PortadorBuilder()
				.id(ID_PORTADOR)
				.nome(NOME_PORTADOR)
				.cpfCnpj(CPF_CNPJ)
				.telefone(TELEFONE)
				.build();

		endereco = new Endereco.EnderecoBuilder()
				.cep("JUnit - 58015720")
				.bairro("JUnit - Jaguaribe")
				.cidade("JUnit - Joao Pessoa")
				.complemento("JUnit - Rua sem saida")
				.logradouro("JUnit - Rua Cipriano Galvao")
				.uf("JUnit - PB")
				.build();

		portador.setEndereco(endereco);
	}
	
	@DisplayName("*** Cadastrar Portador EM LOTE- Controller ***")
	@Test
	public void gerarLoteTest() throws Exception {
		
		//mocka o service com o metodo gerarLote()
		Mockito.when(portadorService.gerarLote()).thenReturn(Collections.singletonList(portador));
		
		// verifica se a chamada da api, recebendo um json e retornando um json está retornando 200
		mockMvc.perform(MockMvcRequestBuilders.post(url+"/gerarlote")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		// verifica se o service é chamado apenas uma vez
		Mockito.verify(portadorService).gerarLote();
		
		// verifica se o service não é chamado mais de uma vez
		Mockito.verifyNoMoreInteractions(portadorService);
	}
	
	@Test
	public void getPortadoresTest() throws Exception {
		// mocka o service para o metodo getPortadores()
		Mockito.when(portadorService.getPortadores()).thenReturn(Collections.singletonList(portador));
		
		// monta a requisição de um metodo get sem json
		mockMvc.perform(MockMvcRequestBuilders.get(url+"/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());

		// verifica se o service é chamado apenas uma vez
		Mockito.verify(portadorService).getPortadores();
		
		// verifica se o service não é chamado mais de uma vez
		Mockito.verifyNoMoreInteractions(portadorService);
	}
}
