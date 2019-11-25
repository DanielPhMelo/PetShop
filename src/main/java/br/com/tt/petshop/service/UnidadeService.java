package br.com.tt.petshop.service;

import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.repository.UnidadeRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public void salvar(Unidade unidade){
        unidadeRepository.save(unidade);
    }

    public List<Unidade> listar(){
        return unidadeRepository.findAll();
    }

    public List<Unidade> listarPorNome(String nome){
        Unidade unidadeExample = new Unidade();
        unidadeExample.setNome(nome);
        Example<Unidade> example = Example.of(unidadeExample);
        return unidadeRepository.findAll(example);
    }
}
