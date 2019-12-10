package br.com.tt.petshop.repository;

import br.com.tt.petshop.dto.RelatorioClienteDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RelatorioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RelatorioClienteDto> listarClientes (){
        Query query = entityManager.createQuery("select " +
                " new br.com.tt.petshop.dto.RelatorioClienteDto(c.nome, c.cpf, c.unidade.id, c.unidade.nome) " +
                "from Cliente c");
        return query.getResultList();
    }
}
