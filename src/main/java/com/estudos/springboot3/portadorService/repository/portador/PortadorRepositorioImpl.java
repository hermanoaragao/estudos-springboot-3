package com.estudos.springboot3.portadorService.repository.portador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.estudos.springboot3.portadorService.entity.portador.Portador;

@Repository
public class PortadorRepositorioImpl {

	// simula a base de dados
	ArrayList<Portador> listaPortador = new ArrayList<Portador>();


	public Portador save(Portador p) {
		p.setId(""+listaPortador.size()+1);
		listaPortador.add(p);
		return p;
	}

	public Portador update(Portador p) {
		for(int i=0; i <= listaPortador.size(); i++) {
			if(listaPortador.get(i).getCpfCnpj().equalsIgnoreCase(p.getCpfCnpj())){

				listaPortador.get(i).setNome(p.getNome());
				listaPortador.get(i).setTelefone(p.getTelefone());
				p = listaPortador.get(i);
				break;
			}
		}
		return p;
	}

	public void delete(Portador p) {
		for(int i=0; i < listaPortador.size(); i++) {
			if(listaPortador.get(i).getCpfCnpj().equalsIgnoreCase(p.getCpfCnpj())){
				listaPortador.remove(i);
				break;
			}
		}
	}

	public Portador getById(long id) {

		for (int i = 0; i < listaPortador.size(); i++) {
			if(listaPortador.get(i).getId().equals(id))
				return listaPortador.get(i);
		}
		return null;
	}

	public Portador getByCpfCnpj(String cpfCnpj) {
		for (int i = 0; i < listaPortador.size(); i++) {
			if(listaPortador.get(i).getCpfCnpj().equalsIgnoreCase(cpfCnpj))
				return listaPortador.get(i);
		}
		return null;
	}


	public List<Portador> getPortadores() {
		return listaPortador;
	}

}
