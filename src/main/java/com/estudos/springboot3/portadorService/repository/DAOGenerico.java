package com.estudos.springboot3.portadorService.repository;

import java.util.List;

public interface DAOGenerico {

	Object save(Object p);
	
	Object update(Object p);

	void delete(Object p);

	Object getById(long id);

	Object getByCpfCnpj(String cpfCnpj);

	List<Object> get();
}
