package com.estudos.springboot3.portadorService.entity;

import org.springframework.data.annotation.Id;

public class ObjetoGenerico {

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
