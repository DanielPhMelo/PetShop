package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.UnidadeInDTO;
import br.com.tt.petshop.dto.UnidadeOutDTO;
import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.service.UnidadeService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/unidades")
public class UnidadeEndpoint {

    private final UnidadeService unidadeService;
    private final ModelMapper mapper;

    public UnidadeEndpoint(UnidadeService unidadeService, ModelMapper mapper) {
        this.unidadeService = unidadeService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UnidadeOutDTO>> buscar(){

        List<UnidadeOutDTO> unidades =
                unidadeService.listar()
                .stream()
                .map((uni) -> mapper.map(uni, UnidadeOutDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeOutDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(mapper.map(unidadeService.buscarPorId(id), UnidadeOutDTO.class));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UnidadeInDTO unidadeInDTO){
        Unidade unidade = mapper.map(unidadeInDTO, Unidade.class);
        Unidade unidadeSalva = unidadeService.salvar(unidade);
        Long idCriado = unidadeSalva.getId();
        return ResponseEntity.created(URI.create(String.format("unidades/")+idCriado.toString())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagar(@PathVariable Long id){
        unidadeService.apagarPorId(id);
        return ResponseEntity.noContent().build();
    }



}


