package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.AnimalInDto;
import br.com.tt.petshop.dto.AnimalOutDto;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.service.AnimalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animais")
public class AnimalEndpoint {

    private final AnimalService animalService;
    private final ModelMapper mapper;

    public AnimalEndpoint(AnimalService animalService, ModelMapper mapper) {
        this.animalService = animalService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<AnimalOutDto>> listar(
            @RequestParam("nome") Optional<String> nome,
            @RequestParam("idCliente") Optional<Long> idCliente,
            @RequestParam("idUnidade") Optional<Long> idUnidade
            ){
        List<Animal> lista = animalService.listar(nome, idCliente, idUnidade);

        List<AnimalOutDto> listaDto = lista
                .stream()
                .map((animal) -> mapper.map(animal, AnimalOutDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaDto);
    }

    @PostMapping
    public ResponseEntity criar(@Valid @RequestBody AnimalInDto animalDto){
        Animal animalSalvo = animalService.salvar(animalDto);
        String url = String.format("/animais/%d", animalSalvo.getId());
        return ResponseEntity.created(URI.create(url)).build();
    }
}