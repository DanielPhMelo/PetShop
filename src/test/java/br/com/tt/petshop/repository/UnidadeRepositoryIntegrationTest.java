package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Unidade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Sql(value="classpath:sql/insert_test_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value="classpath:sql/delete_all_test_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UnidadeRepositoryIntegrationTest {
    @Autowired
    private UnidadeRepository unidadeRepository;

    @Test
    public void deveriaListarPorNome(){
        //arrange
        List<Unidade> unidades;
        Unidade unidade;

        //act
        unidades = unidadeRepository.listarPorNome("Pet Shop do Sven");

        //assert
        Assertions.assertEquals(1, unidades.size());
        unidade = unidades.get(0);
        Assertions.assertEquals("Pet Shop do Sven", unidade.getNome());
    }

    @Test
    public void deveriaFalharBuscaPorNome(){
        //Arrange
        List<Unidade> unidades;
        Unidade unidade;

        //Act
        unidades = unidadeRepository.listarPorNome("Nome Inexistente");

        //Assert
        Assertions.assertEquals(0, unidades.size());
    }
}