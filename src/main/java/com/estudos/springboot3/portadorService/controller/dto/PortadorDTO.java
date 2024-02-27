package com.estudos.springboot3.portadorService.controller.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.estudos.springboot3.portadorService.entity.portador.Portador;

public class PortadorDTO implements Serializable{

	private static final long serialVersionUID = 6202074536180926414L;
	
	private String id;
	private String nome;
	private String cpfCnpj;
	private String telefone;
	private EnderecoDTO endereco;
	
	
	public PortadorDTO() {
		super();
	}
	
	// usado no cadastro
	public PortadorDTO(String nome, String cpfCnpj, String telefone) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
	}

	// padrão builder
	public static class PortadorDTOBuilder{

		private String nome;
		private String cpfCnpj;
		private String telefone;
		

		public PortadorDTOBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public PortadorDTOBuilder cpfCnpj(String cpfCnpj) {
			this.cpfCnpj = cpfCnpj;
			return this;
		}

		public PortadorDTOBuilder telefone(String telefone) {
			this.telefone = telefone;
			return this;
		}

		
		public PortadorDTO build(){
			return new PortadorDTO(nome,cpfCnpj,telefone);
		}
	}
	
	
	// criando um conversor
	public static PortadorDTO convert(Portador portador)
	{
		PortadorDTO portadorDTO = new PortadorDTO();
		
		//dto.setId(portador.getId());
		BeanUtils.copyProperties(portador, portadorDTO);
		
		if(portador.getEndereco() != null) {
			//EnderecoDTO dtoEndereco = new EnderecoDTO.EnderecoDTOBuilder()
			EnderecoDTO dtoEndereco = new EnderecoDTO();
			BeanUtils.copyProperties(portador.getEndereco(), dtoEndereco);
			portadorDTO.setEndereco(dtoEndereco);
		}
				
		return portadorDTO;
	}
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	

}
