package com.estudos.springboot3.portadorService.service.portador;

import java.util.List;

import com.estudos.springboot3.portadorService.entity.portador.Portador;

public interface PortadorService {

	Portador save(Portador p);
	
	Portador update(Portador p);
	
	void delete(Portador p);
	
	Portador getById(long id);
	
	Portador getByCpfCnpj(String cpfCnpj);
	
	List<Portador> getPortadores();
	
	List<Portador> gerarLote();
	
}
