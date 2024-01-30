package com.estudos.springboot3.portadorService.repository.portador;

import java.util.List;

import com.estudos.springboot3.portadorService.entity.portador.Portador;

public interface PortadorRepositorio {

	Portador save(Portador p);
	
	Portador update(Portador p);

	void delete(Portador p);

	Portador getById(long id);

	Portador getByCpfCnpj(String cpfCnpj);

	List<Portador> getPortadores();
}
