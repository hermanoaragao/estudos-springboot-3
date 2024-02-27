package com.estudos.springboot3.portadorService.repository.portador;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.estudos.springboot3.portadorService.entity.portador.Portador;

public interface PortadorRepositorio extends MongoRepository<Portador, Long> {

	Portador save(Portador p);
	
	Portador update(Portador p);

	void delete(Portador p);

	Portador getById(long id);

	Portador getByCpfCnpj(String cpfCnpj);

	List<Portador> getPortadores();
}
