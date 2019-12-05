package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ExtendWith(SpringExtension.class)
class ClienteRepositoryIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void deveriaListarPorNome(){
        //arrange
        List<Cliente> clientes;
        Cliente cliente;

        //act
        clientes = clienteRepository.listarPorNome("Seu Adolfo Brabo");

        //assert
        cliente = clientes.get(0);
        Assertions.assertEquals("Seu Adolfo Brabo", cliente.getNome());
    }
}