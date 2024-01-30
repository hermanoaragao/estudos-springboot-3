package com.estudos.springboot3.portadorService.service.portador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.estudos.springboot3.portadorService.entity.portador.Portador;
import com.estudos.springboot3.portadorService.repository.portador.PortadorRepositorio;
import com.estudos.springboot3.portadorService.service.endereco.EnderecoService;


@Service
public class PortadorServiceImpl implements PortadorService{

	/*
	@Autowired
	PortadorDAO portadorDAO;
	
	@Autowired
	EnderecoServiceImpl enderecoService;
	*/
	
	PortadorRepositorio portadorDAO;
	EnderecoService enderecoService;
	
	
	public PortadorServiceImpl(PortadorRepositorio portadorDAO, EnderecoService	 enderecoService) {
		super();
		this.portadorDAO = portadorDAO;
		this.enderecoService = enderecoService;
	}

	public Portador save(Portador portador) {
		
		portador.setEndereco(enderecoService.gerarEndereco());
		portadorDAO.save(portador);
		return portador;
	}

	public Portador update(Portador p) {
		return portadorDAO.update(p);
	}

	public void delete(Portador p) {
		portadorDAO.delete(p);
	}

	public Portador getById(long id) {
		return portadorDAO.getById(id);
	}

	public Portador getByCpfCnpj(String cpfCnpj) {
		return portadorDAO.getByCpfCnpj(cpfCnpj);
	}

	public List<Portador> getPortadores() {
		return portadorDAO.getPortadores();
	}

	public List<Portador> gerarLote() {
		
		String nomesMasculinos[] = {"Hermano","Paulo","Marcus","Vitor","Vinicius","Lucas","Matheus","Joao","Judas","Tiago","Bernardo","Benjamin","Bruno","Benício","Heitor","Henrique","Hugo","Carlos","Cauã","Cleber"};
		String sobrenomes[] = {"Aragao","Almeida","Batista","Ramalho","Matos","Macedo","Machado","Costa","Bezerra","Silva"};
		String cpfCnpjs[] = {"27458041030","95823967098","65274041078","60794262007","26018301075","80099936038","27672081047","31693623056","52787156002","42696698023"};
		String telefones[] = {"(21) 3557-6332","(27) 3746-1321","(62) 2845-8213","(69) 3166-2432","(67) 3282-1196","(82) 3656-7788","(95) 3414-1107","(94) 3447-6237","(33) 2725-7983","(88) 2144-8749"};
		
		Arrays.sort(nomesMasculinos);
		Random gerador = new Random();
		
		List<Portador> loteCadastrado = new ArrayList<Portador>();
		
		for (int i = 0; i < nomesMasculinos.length; i++) {
			Portador p = new Portador.PortadorBuilder()
					.nome(nomesMasculinos[i]+" "+sobrenomes[gerador.nextInt(sobrenomes.length)])
					.cpfCnpj(cpfCnpjs[gerador.nextInt(cpfCnpjs.length)])
					.telefone(telefones[gerador.nextInt(telefones.length)])
					.build();
			p.setEndereco(enderecoService.gerarEndereco());
			portadorDAO.save(p);
			loteCadastrado.add(p);
		}
		
		return loteCadastrado;
	}

}
