package br.com.tt.petshop.service;

import br.com.tt.petshop.dto.RelatorioClienteDto;

import java.util.List;

public interface RelatorioService {
    List<RelatorioClienteDto> listarClientes();
}
