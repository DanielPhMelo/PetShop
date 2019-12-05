package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("select cli from Cliente cli where cli.nome like :nomeCliente")
    List<Cliente> listarPorNome(@Param("nomeCliente") String nome);

    @Query("select cli from Cliente cli where cli.cpf like :cpfCliente")
    List<Cliente> listarPorCpf(@Param("cpfCliente") String cpf);

    @Query("select cli from Cliente cli where cli.nome like :nomeCliente or cli.cpf like :cpfCliente")
    List<Cliente> listarPorCpfOuNome(@Param("nomeCliente")String nome,
                                     @Param("cpfCliente") String cpf);

    List<Cliente> findByNome(String nome);
    List<Cliente> findByCpf(String cpf);
    List<Cliente> findByNomeOrCpf(String nome, String cpf);
}

