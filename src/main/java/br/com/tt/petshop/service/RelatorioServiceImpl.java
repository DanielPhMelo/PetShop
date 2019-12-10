package br.com.tt.petshop.service;

import br.com.tt.petshop.dto.RelatorioClienteDto;
import br.com.tt.petshop.repository.RelatorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioServiceImpl implements RelatorioService {

    private final RelatorioRepository relatorioRepository;

    public RelatorioServiceImpl(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    @Override
    public List<RelatorioClienteDto> listarClientes() {
        return relatorioRepository.listarClientes();
    }
}
