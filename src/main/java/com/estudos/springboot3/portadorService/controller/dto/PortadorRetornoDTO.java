package com.estudos.springboot3.portadorService.controller.dto;

public class PortadorRetornoDTO {

	private String id;
	private String nome;
	private String cpfCnpj;
	private String telefone;
	

	// usado no cadastro
	public PortadorRetornoDTO(String id, String nome, String cpfCnpj, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
	}

	// padrão Builder
	public static class PortadorRetornoDTOBuilder{

		private String id;
		private String nome;
		private String cpfCnpj;
		private String telefone;
		
		public PortadorRetornoDTOBuilder id(String id)
		{
			this.id = id;
			return this;
		}

		public PortadorRetornoDTOBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public PortadorRetornoDTOBuilder cpfCnpj(String cpfCnpj) {
			this.cpfCnpj = cpfCnpj;
			return this;
		}

		public PortadorRetornoDTOBuilder telefone(String telefone) {
			this.telefone = telefone;
			return this;
		}

		public PortadorRetornoDTO build(){
			return new PortadorRetornoDTO(id, nome,cpfCnpj,telefone);
		}
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
	
}
