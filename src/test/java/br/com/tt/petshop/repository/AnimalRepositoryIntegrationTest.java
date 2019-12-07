package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Sql(value="classpath:sql/insert_test_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value="classpath:sql/delete_all_test_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AnimalRepositoryIntegrationTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void deveriaListarPorNome(){
        //arrange
        List<Animal> animais;
        Animal animal;

        //act
        animais = animalRepository.listarPorNome("Seu Adolfo Brabo");

        //assert
        animal = animais.get(0);
        Assertions.assertEquals("Seu Adolfo Brabo", animal.getNome());
    }

}
