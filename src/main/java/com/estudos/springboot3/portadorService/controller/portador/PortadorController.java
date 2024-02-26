package com.estudos.springboot3.portadorService.controller.portador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.springboot3.portadorService.controller.dto.PortadorDTO;
import com.estudos.springboot3.portadorService.entity.endereco.Endereco;
import com.estudos.springboot3.portadorService.entity.portador.Portador;
import com.estudos.springboot3.portadorService.service.portador.PortadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/portadores", produces = {"application/json"})
public class PortadorController {

	
	private PortadorService portadorService;
	

	public PortadorController(PortadorService portadorService) {
		super();
		this.portadorService = portadorService;
	}
	

	/**
	 * Metodo de cadastro de portador.
	 * Recebe dados basicos (Nome, cpf e telefone) e salva um portador com endereco aleatorio
	 * @param dto
	 * @return
	 */
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@Operation(summary = "Recebe os dados de um portador e armazena em base", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastro processado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não autenticado")
	})
	public ResponseEntity<Portador> cadastrar(@RequestBody PortadorDTO dto)
	{
		// monta um portador usando o padrão builder
		Portador p = new Portador.PortadorBuilder()
				.nome(dto.getNome())
				.cpfCnpj(dto.getCpfCnpj())
				.telefone(dto.getTelefone())
				.build();
		
		if(dto.getEndereco() != null) {
			Endereco end = new Endereco.EnderecoBuilder()
					.cep(dto.getEndereco().getCep())
					.bairro(dto.getEndereco().getBairro())
					.cidade(dto.getEndereco().getCidade())
					.complemento(dto.getEndereco().getComplemento())
					.logradouro(dto.getEndereco().getLogradouro())
					.uf(dto.getEndereco().getUf())
					.build();
			p.setEndereco(end);
		}
		
		// chama a classe de negócio do portador
		portadorService.save(p);
		return ResponseEntity.ok(p);
	}
	
	/**
	 * Metodo que aceita uma lista de portadores com dados basicos
	 * @param listPortadorDTO
	 * @return
	 */
	@PostMapping("/lote")
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@Operation(summary = "Recebe um lote de portadores e armazana em base", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastro processado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não autenticado")
	})
	public ResponseEntity<List<Portador>> cadastrarLote(@RequestBody List<PortadorDTO> listPortadorDTO)
	{
		List<Portador> listRetorno = new ArrayList<Portador>();
        for (PortadorDTO portadorDTO : listPortadorDTO) {
            // monta um portador usando o padrão builder
            Portador p = new Portador.PortadorBuilder()
                    .nome(portadorDTO.getNome())
                    .cpfCnpj(portadorDTO.getCpfCnpj())
                    .telefone(portadorDTO.getTelefone())
                    .build();

            // chama a classe de negócio do portador
            portadorService.save(p);
            listRetorno.add(p);
        }
		return ResponseEntity.ok(listRetorno);
	}
	
	/**
	 * Metodo que gera um lote de 20 portadores a cada execução
	 * @return
	 */
	@PostMapping("/gerarlote")
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@Operation(summary = "Gera um lote de 20 portadores aleatórios", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastro processado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não autenticado")
	})
	public ResponseEntity<List<PortadorDTO>> gerarLote()
	{
		List<PortadorDTO> listaRetornoDTO = new ArrayList<PortadorDTO>();
		// chama a classe de negócio do portador
		List<Portador> listaLotePortador = portadorService.gerarLote();

        for (Portador portador : listaLotePortador) {
            PortadorDTO dto = PortadorDTO.convert(portador);
            listaRetornoDTO.add(dto);
        }
		return ResponseEntity.ok(listaRetornoDTO);
	}
	
	
	/**
	 * Metodo que retorna todos os portadores cadastrados
	 * @return
	 */
	@GetMapping()
	@PreAuthorize("hasRole('ROLE_USUARIO')")
	@Operation(summary = "Retorna todos os portadores cadastrados", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Requisição processada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não autenticado")
	})
	public ResponseEntity<List<PortadorDTO>> getPortadores()
	{
		List<PortadorDTO> listaRetorno = new ArrayList<PortadorDTO>();
		List<Portador> listaCadastrada = portadorService.getPortadores();
        for (Portador portador : listaCadastrada) {
            PortadorDTO dto = PortadorDTO.convert(portador);
            listaRetorno.add(dto);
        }
		return ResponseEntity.ok(listaRetorno);
	}
	
	
	/**
	 * Metodo que remove um portador. A verificacao se dá pelo cpf.
	 * @param dto
	 */
	@DeleteMapping
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@Operation(summary = "Apaga um portador cadastrado com o mesmo CPF do portador passado como parâmetro", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Requisição processada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não autenticado")
	})
	public void remover(@RequestBody PortadorDTO dto)
	{
		// monta um portador usando o padrão builder
		Portador p = new Portador.PortadorBuilder()
				.nome(dto.getNome())
				.cpfCnpj(dto.getCpfCnpj())
				.telefone(dto.getTelefone())
				.build();
		
		// chama a classe de negócio do portador
		portadorService.delete(p);
		//return ResponseEntity.ok(p);
	}
	
	
	/**
	 * Metodo que altera um portador (nome e telefone). A verificacao se dá pelo cpf.
	 * @param dto
	 */
	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	public ResponseEntity<Portador> alterar(@RequestBody PortadorDTO dto)
	{
		// monta um portador usando o padrão builder
		Portador p = new Portador.PortadorBuilder()
				.nome(dto.getNome())
				.cpfCnpj(dto.getCpfCnpj())
				.telefone(dto.getTelefone())
				.build();
		
		// chama a classe de negócio do portador
		portadorService.update(p);
		return ResponseEntity.ok(p);
	}
	   
	
	
}
