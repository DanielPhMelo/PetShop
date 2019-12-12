package br.com.tt.petshop.api;

import br.com.tt.petshop.config.ModelMapperConfig;
import br.com.tt.petshop.dto.ClienteInDto;
import br.com.tt.petshop.dto.ClienteOutDto;
import br.com.tt.petshop.exceptions.NegocioException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity criar(
            @RequestBody ClienteInDto clienteInDto)
            throws NegocioException {
        Cliente cliente = mapper.map(clienteInDto, Cliente.class);
        Cliente clienteSalvo = clienteService.salvar(cliente);

        URI location = URI.create(String.format("clientes/%d", clienteSalvo.getId()));

        return ResponseEntity.created(location).build();
    }

}
