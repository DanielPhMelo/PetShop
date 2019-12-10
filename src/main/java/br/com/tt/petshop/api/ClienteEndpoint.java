package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.ClienteOutDto;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteEndpoint {
    private final ModelMapper mapper;
    private final ClienteService clienteService;

    public ClienteEndpoint(ModelMapper mapper, ClienteService clienteService) {
        this.mapper = mapper;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteOutDto>> listar(
        @RequestParam("nome") Optional<String> nome,
        @RequestParam("cpf") Optional<String> cpf)
    {
        List<Cliente> clientes = clienteService.listar(nome, cpf);
        List<ClienteOutDto> clientesDto = clientes
                .stream()
                .map((cliente)-> mapper.map(cliente, ClienteOutDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientesDto);
    }

}
