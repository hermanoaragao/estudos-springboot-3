package com.estudos.springboot3.portadorService.service.portador;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.estudos.springboot3.portadorService.entity.endereco.Endereco;
import com.estudos.springboot3.portadorService.entity.portador.Portador;
import com.estudos.springboot3.portadorService.repository.portador.PortadorRepositorioImpl;
import com.estudos.springboot3.portadorService.service.endereco.EnderecoService;
import com.estudos.springboot3.portadorService.service.portador.PortadorServiceImpl;

@SpringBootTest
public class PortadorServiceImplTest {

	public static final Long ID_PORTADOR = 100L;
    public static final String NOME_PORTADOR = "Portador JUnit";
    public static final String CPF_CNPJ = "00812926439";
    public static final String TELEFONE = "996745900";
	
	
    @InjectMocks
	private PortadorServiceImpl portadorService;
    
    @Mock
	private EnderecoService enderecoService;
    
    @Mock
	private PortadorRepositorioImpl portadorDAO;
	
    private Portador portador;

    private Endereco endereco;
	
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		startPortador();
	}
	
	
	private void startPortador(){

        portador = new Portador.PortadorBuilder()
                .id(Long.parseLong("100"))
                .nome(NOME_PORTADOR)
                .cpfCnpj(CPF_CNPJ)
                .telefone(TELEFONE)
                .build();

        portador.setId(1l);

        endereco = new Endereco.EnderecoBuilder()
                .cep("58015720")
                .bairro("Jaguaribe")
                .cidade("Joao Pessoa")
                .complemento("Rua sem saida")
                .logradouro("Rua Cipriano Galvao")
                .uf("PB")
                .build();

        portador.setEndereco(endereco);
    }
	
	
	@DisplayName("*** saveTest() -> Metodo save ***")
    @Test
    void saveTest() {
        // Pré condicao para chamar o portador service é existir um portadorDAO
        // mocakndo o repositorio, para quando o mesmo for chamado, retornar o portados padrao
        Mockito.when(portadorDAO.save(portador)).thenReturn(portador);

        // Pré condicao para chamar o portador service é existir um enderecoService
        // mockando o service de endereco para quando for chamado, retornar um endereco padrao
        Mockito.when(enderecoService.gerarEndereco()).thenReturn(endereco);

        // faz a chamada do metodo save() do Service
        Portador response = portadorService.save(portador);

        // objeto nao pode ser null
        Assertions.assertNotNull(response);

        // verifica se o objeto que foi retornado é do tipo Portador
        Assertions.assertEquals(Portador.class, response.getClass());

        // verifica se o portador cadastrado possui ID
        Assertions.assertNotNull(response.getId());

        // verifica se o portador cadastrado possui CPF_CNPJ
        Assertions.assertNotNull(response.getCpfCnpj());

        // verifica se o portador cadastrado possui endereco
        Assertions.assertNotNull(response.getEndereco());
        
        
        Assertions.assertAll("Todas as validacoes de portador",
				()-> Assertions.assertNotNull(response.getId()),
				()-> Assertions.assertNotNull(response.getCpfCnpj()));

        
        Assertions.assertAll("Todas as validacoes do endereco do portador",
				()-> Assertions.assertNotNull(response.getEndereco()),
				()-> Assertions.assertNotNull(response.getEndereco().getCep()),
				()-> Assertions.assertEquals(response.getEndereco().getCep().length(), 8));

        // Verifica se determinado comportamento aconteceu uma vez
        // verifica se o portador service foi chamado apenas uma vez
        Mockito.verify(portadorDAO).save(portador);

        // Verifica se alguma das simulações fornecidas tem alguma interação não verificada.
        // Você pode usar esse método depois de verificar suas simulações - para ter certeza de que nada mais foi invocado em suas simulações.
        Mockito.verifyNoMoreInteractions(portadorDAO);
    }
	
	
	@Test
    void updateTest() {
    }

    @Test
    void deleteTest() {
    }

    @Test
    void getByIdTest() {
        Mockito.when(portadorDAO.getById(Mockito.anyLong())).thenReturn(portador);

        Portador response = portadorService.getById(ID_PORTADOR);
        Assertions.assertEquals(Portador.class, response.getClass());
    }
	
	
	@DisplayName("***Metodo que testa salvar um portador em lote***")
	@Test
	public void gerarLoteTest() {
		
		// Pré condicao para chamar o portador service é existir um portadorDAO
        // mocakndo o repositorio, para quando o mesmo for chamado, retornar o portados padrao
        Mockito.when(portadorDAO.save(portador)).thenReturn(portador);

        // Pré condicao para chamar o portador service é existir um enderecoService
        // mockando o service de endereco para quando for chamado, retornar um endereco padrao
        Mockito.when(enderecoService.gerarEndereco()).thenReturn(endereco);

        List<Portador> responseList = portadorService.gerarLote();

        for (int i = 0; i < responseList.size(); i++) {

            Portador response = responseList.get(i);

            // objeto nao pode ser null
            Assertions.assertNotNull(response);

            // verifica se o objeto que foi retornado é do tipo Portador
            Assertions.assertEquals(Portador.class, response.getClass());

            // verifica se o portador cadastrado possui ID
            //Assertions.assertNotNull(response.getId());

            // verifica se o portador cadastrado possui CPF_CNPJ
            Assertions.assertNotNull(response.getCpfCnpj());

            // verifica se o portador cadastrado possui endereco
            Assertions.assertNotNull(response.getEndereco());
        }

        Assertions.assertEquals(20, responseList.size(), "Lista gerada precisa ser de 20 unidades");
	}
	
}
