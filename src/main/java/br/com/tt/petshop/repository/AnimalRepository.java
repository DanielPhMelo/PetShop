package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.projection.AnimalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("select a from Animal a where a.nome like :nomeAnimal")
    List<Animal> listarPorNome(@Param("nomeAnimal") String nome);

    @Query("select a from Animal a order by a.nome")
    List<AnimalProjection> findAllOrderByNome();
}