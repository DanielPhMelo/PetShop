package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    @Query("select uni from Unidade uni where uni.nome like :nome")
    List<Unidade> listarPorNome(@Param("nome") String nome);
}
