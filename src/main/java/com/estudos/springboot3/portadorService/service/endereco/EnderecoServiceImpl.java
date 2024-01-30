package com.estudos.springboot3.portadorService.service.endereco;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.estudos.springboot3.portadorService.entity.endereco.Endereco;
import com.estudos.springboot3.portadorService.entity.endereco.Endereco.EnderecoBuilder;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	private Long idEndereco = 0L; 
	
	/**
	 * Gerador aleatório de endereços
	 * @return
	 */
	public Endereco gerarEndereco(){

		Random gerador = new Random();
		
		String ceps [] = {"77006892","68909144","69906474","65919311","57602140","79311180","74925782","49031050","35160118","58015720"};
		String logradouros [] = {"Quadra 203 Norte Alameda 7","Avenida Elis Regina","Rua Baguari 44","Rua dos Biguás","Rua José Amaral 750","Rua Piauí 185","Rua Izídio Inácio da Silva 987","Rua Edson da Cunha Lima 851","Avenida Japão","Rua Cipriano Galvão 44"};
		String bairros [] = {"Plano Diretor Norte","Boné Azul","Rio Branco","Santa Inês","Paraíso","Cristo Redentor","Setor Franco","Farolândia","Cariru","Jaguaribe"};
		String cidades [] = {"Palmas","Macapá","Rio Branco","Imperatriz","Palmeira dos Índios","Corumbá","Aparecida de Goiânia","Aracaju","Ipatinga","João Pessoa"};
		String ufs [] = {"TO","AP","AC","MA","AL","MS","GO","SE","MG","PB"};
		String complementos [] = {"Casa","Apto","Banca 01","Loja B","Corredor 3","Ala 07","Box 40","Travessa esquerda","Quadra H","Rua Sem Saída"};
		
		Endereco endereco = new EnderecoBuilder()
				.cep(ceps[gerador.nextInt(ceps.length)])
				.logradouro(logradouros[gerador.nextInt(logradouros.length)])
				.bairro(bairros[gerador.nextInt(bairros.length)])
				.cidade(cidades[gerador.nextInt(cidades.length)])
				.uf(ufs[gerador.nextInt(ufs.length)])
				.complemento(complementos[gerador.nextInt(complementos.length)])
				.build();
		idEndereco++;
		endereco.setId(idEndereco);
		return endereco;
	}
}
