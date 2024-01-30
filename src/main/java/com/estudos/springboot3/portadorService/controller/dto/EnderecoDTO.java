package com.estudos.springboot3.portadorService.controller.dto;

import java.io.Serializable;

public class EnderecoDTO implements Serializable{

	private static final long serialVersionUID = 6487121047070691603L;

	private String cep;
	private String logradouro;
	private String bairro;
	private String uf;
	private String cidade;
	private String complemento;
	
	
	
	public EnderecoDTO() {
		super();
	}
	public EnderecoDTO(String cep, String logradouro, String bairro, String uf, String cidade, String complemento) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.uf = uf;
		this.cidade = cidade;
		this.complemento = complemento;
	}
	
	// Padrão Builder
	public static class EnderecoDTOBuilder{
		private String cep;
		private String logradouro;
		private String bairro;
		private String uf;
		private String cidade;
		private String complemento;
		
		public EnderecoDTOBuilder cep(String cep) {
			this.cep = cep;
			return this;
		}
		
		public EnderecoDTOBuilder logradouro(String logradouro) {
			this.logradouro = logradouro;
			return this;
		}
		
		public EnderecoDTOBuilder bairro(String bairro) {
			this.bairro = bairro;
			return this;
		}
		
		public EnderecoDTOBuilder uf(String uf) {
			this.uf = uf;
			return this;
		}
		
		public EnderecoDTOBuilder cidade(String cidade) {
			this.cidade = cidade;
			return this;
		}
		
		public EnderecoDTOBuilder complemento(String complemento) {
			this.complemento = complemento;
			return this;
		}
		
		public EnderecoDTO build()
		{
			return new EnderecoDTO(cep, logradouro, bairro, uf, cidade, complemento);
		}
	}
	
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	 
	
}
