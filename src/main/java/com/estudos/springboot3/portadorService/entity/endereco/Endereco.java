package com.estudos.springboot3.portadorService.entity.endereco;

import com.estudos.springboot3.portadorService.entity.ObjetoGenerico;

public class Endereco extends ObjetoGenerico{

	private String cep;
	private String logradouro;
	private String bairro;
	private String uf;
	private String cidade;
	private String complemento;
	
	
	public Endereco(String cep, String logradouro, String bairro, String uf, String cidade, String complemento) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.uf = uf;
		this.cidade = cidade;
		this.complemento = complemento;
	}
	
	public static class EnderecoBuilder{
	
		private String cep;
		private String logradouro;
		private String bairro;
		private String uf;
		private String cidade;
		private String complemento;
		
		
		public EnderecoBuilder cep(String cep) {
			this.cep = cep;
			return this;
		}
		
		public EnderecoBuilder logradouro(String logradouro) {
			this.logradouro = logradouro;
			return this;
		}
		
		public EnderecoBuilder bairro(String bairro) {
			this.bairro = bairro;
			return this;
		}
		
		public EnderecoBuilder uf(String uf) {
			this.uf = uf;
			return this;
		}
		
		public EnderecoBuilder cidade(String cidade) {
			this.cidade = cidade;
			return this;
		}
		
		public EnderecoBuilder complemento(String complemento) {
			this.complemento = complemento;
			return this;
		}
		
		public Endereco build() {
			return new Endereco(cep, logradouro, bairro, uf, cidade, complemento);
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
