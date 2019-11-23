package br.com.tt.petshop.service;

import br.com.tt.petshop.exceptions.NegocioException;
import br.com.tt.petshop.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteServiceTest {

    private ClienteService clienteService;
    private static final String CPF_VALIDO = "123.456.789-11";
    private static final String NOME_VALIDO = "Daniel Melo";

    @BeforeEach
    public void inicializa(){
        clienteService = new ClienteService(clienteRepository);
    }

    @Test
    public void deveriaSalvarComSucesso(){
        //Arrange
        Cliente cliente = new Cliente();
        cliente.setNome(NOME_VALIDO);
        cliente.setCpf(CPF_VALIDO);

        //Act


        //Assert

    }

    @Test
    public void deveriaFalharComNomeSemSobrenome(){
        //Arrange
        String nome = "Daniel";
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(CPF_VALIDO);

        //Act
        Exception exception = Assertions.assertThrows(NegocioException.class, () -> clienteService.salvar(cliente));

        //Assert
        Assertions.assertEquals("O nome da pessoa deve ser composto de 2 partes", exception.getMessage());
    }

    @Test
    public void deveriaFalharComCpfMenorQueOnze(){
        //Arrange
        Cliente cliente = new Cliente();
        String cpf = "123.456.789-1";
        cliente.setCpf(cpf);
        cliente.setNome(NOME_VALIDO);

        //Act
        Exception exception = Assertions.assertThrows(NegocioException.class, () -> clienteService.salvar(cliente));

        //Assert
        Assertions.assertEquals("CPF Inválido", exception.getMessage());
    }

    @Test
    public void deveriaFalharComCpfMaiorQueOnze(){
        //Arrange
        Cliente cliente = new Cliente();
        String cpf = "123.456.789-111";
        cliente.setCpf(cpf);
        cliente.setNome(NOME_VALIDO);

        //Act
        Exception exception = Assertions.assertThrows(NegocioException.class, () -> clienteService.salvar(cliente));

        //Assert
        Assertions.assertEquals("CPF Inválido", exception.getMessage());
    }

    @Test
    public void deveriaFalharComCpfConsiderandoFormatacao(){
        //Arrange
        Cliente cliente = new Cliente();
        String cpf = "123.456.789";
        cliente.setCpf(cpf);
        cliente.setNome(NOME_VALIDO);

        //Act
        Exception exception = Assertions.assertThrows(NegocioException.class, () -> clienteService.salvar(cliente));

        //Assert
        Assertions.assertEquals("CPF Inválido", exception.getMessage());
    }

    @Test
    public void deveriaFalharSeParteDoNomeMenorQue2(){
        //Arrange
        Cliente cliente = new Cliente();
        cliente.setCpf(CPF_VALIDO);
        cliente.setNome("D A");

        //Act
        Exception exception = Assertions.assertThrows(NegocioException.class, () -> clienteService.salvar(cliente));

        //Assert
        Assertions.assertEquals("Nomes e Sobrenomes devem conter ao menos 2 caracteres", exception.getMessage());
    }
}