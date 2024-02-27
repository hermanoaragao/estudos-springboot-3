package com.estudos.springboot3.portadorService.entity.portador;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.estudos.springboot3.portadorService.entity.ObjetoGenerico;
import com.estudos.springboot3.portadorService.entity.endereco.Endereco;

@Document
public class Portador {

	@Id
	private String id;
	
	private String nome;
	private String cpfCnpj;
	private String telefone;
	private Endereco endereco;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(cpfCnpj, endereco, nome, telefone);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portador other = (Portador) obj;
		return Objects.equals(cpfCnpj, other.cpfCnpj) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}
	
	private Portador(String nome, String cpfCnpj, String telefone) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
	}

	public static class PortadorBuilder extends ObjetoGenerico{

		private String nome;
		private String cpfCnpj;
		private String telefone;

		public PortadorBuilder id(Long id) {
			setId(id);
			return this;
		}

		public PortadorBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public PortadorBuilder cpfCnpj(String cpfCnpj) {
			this.cpfCnpj = cpfCnpj;
			return this;
		}
		
		public PortadorBuilder telefone(String telefone) {
			this.telefone = telefone;
			return this;
		}
		
		public Portador build(){
			return new Portador(nome,cpfCnpj,telefone);
		}
	}
	
	
	
	
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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