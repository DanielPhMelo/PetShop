package br.com.tt.petshop.service;

import br.com.tt.petshop.exceptions.NegocioException;
import br.com.tt.petshop.exceptions.RegistroNaoExisteException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ClienteService  {
    private static final int QUANTIDADE_MINIMA_PARTES_NOME = 2;
    private static final int TAMANHO_CPF = 11;
    private static final int TAMANHO_PARTE_NOME = 2;

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar(Optional<String> nome, Optional<String> cpf) {
        if (nome.isPresent() && cpf.isPresent()) {
            return clienteRepository.listarPorCpfOuNome(nome.get(), cpf.get());

        } else if (nome.isPresent()) {
            return clienteRepository.listarPorNome(nome.get());

        } else if (cpf.isPresent()) {
            return clienteRepository.listarPorCpf(cpf.get());
        }
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(
            @NotNull (message = "Id do cliente é obrigatório!")
                    Long id){
        Optional<Cliente>  optionalCliente = clienteRepository.findById(id);
        return optionalCliente.orElseThrow(() -> new RegistroNaoExisteException("Id do cliente informado não existe"));
    }

    public void salvar(Cliente cliente) throws NegocioException {
        validaQuantidadePartesNome(cliente.getNome());
        validaTamanhoCpf(cliente.getCpf());
        validaTamanhoDasPartesDoNome(cliente.getNome());
        clienteRepository.save(cliente);
    }

    private void validaTamanhoDasPartesDoNome(String nome) throws NegocioException {
        String[] partesDoNome = nome.trim().split(" ");
        for (String parteDoNome: partesDoNome){
            if (parteDoNome.length() < TAMANHO_PARTE_NOME){
                throw new NegocioException(String.format("Nomes e Sobrenomes devem conter ao menos %d caracteres", TAMANHO_PARTE_NOME));
            }
        }
    }

    private void validaTamanhoCpf(String cpf) throws NegocioException {
        int tamanhoCpf = cpf.replaceAll("\\D","").length();
        if (tamanhoCpf != TAMANHO_CPF){
            throw new NegocioException(String.format("CPF Inválido"));
        }
    }

    private void validaQuantidadePartesNome(String nome) throws NegocioException {
        int quantidadePartes = nome.trim().split(" ").length;
        if (quantidadePartes < QUANTIDADE_MINIMA_PARTES_NOME){
            throw new NegocioException(String.format("O nome da pessoa deve ser composto de %d partes", QUANTIDADE_MINIMA_PARTES_NOME));
        }
    }

}
