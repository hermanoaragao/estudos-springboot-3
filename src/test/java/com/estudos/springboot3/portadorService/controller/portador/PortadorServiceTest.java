package com.estudos.springboot3.portadorService.controller.portador;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.estudos.springboot3.portadorService.entity.endereco.Endereco;
import com.estudos.springboot3.portadorService.entity.portador.Portador;
import com.estudos.springboot3.portadorService.repository.portador.PortadorRepositorioImpl;
import com.estudos.springboot3.portadorService.service.endereco.EnderecoService;
import com.estudos.springboot3.portadorService.service.endereco.EnderecoServiceImpl;
import com.estudos.springboot3.portadorService.service.portador.PortadorService;
import com.estudos.springboot3.portadorService.service.portador.PortadorServiceImpl;

public class PortadorServiceTest {

	private PortadorService portadorService;
	private EnderecoService enderecoService;
	private PortadorRepositorioImpl portadorDAO;
	
	
	
	@BeforeEach
	public void setup() {
		
		enderecoService = new EnderecoServiceImpl();
		portadorDAO	 = new PortadorRepositorioImpl();
		portadorService = new PortadorServiceImpl(portadorDAO, enderecoService);
	}
	
	@DisplayName("***Metodo que testa um endereco 100% preenchido***")
	@Test
	public void salvarPortadorComEnderecoCompletoTest() {
		Portador p = new Portador.PortadorBuilder()
				.cpfCnpj("00812926439")
				.nome("hermano")
				.telefone("83996745900")
				.build();
		
		p = portadorService.save(p);
		assertNotNull(p.getEndereco());
		
		Endereco end = p.getEndereco();
		if(end.getId() == null ) {
			fail("Endereco esta null");
		}
		assertNotNull(end.getId());
		assertEquals(end.getCep().length(), 8);
		assertAll("Todas as validacoes",
				()-> assertNotNull(end),
				()-> assertNotNull(end.getId()),
				()-> assertEquals(end.getCep().length(), 8));
		
	}
	
	
	@DisplayName("***Metodo que testa salvar um portador em lote***")
	@Test
	public void gerarLoteTest() {
		
		List<Portador> lista = portadorService.gerarLote();
		
		assertEquals(lista.size(), 20);
	}
	
}
