package br.com.tt.petshop.api;

import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.service.UnidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unidades")
public class UnidadeEndpoint {

    private final UnidadeService unidadeService;

    public UnidadeEndpoint(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Unidade>> buscar(){
        return ResponseEntity.ok(unidadeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> buscar(@PathVariable Long id){
        Unidade unidade = unidadeService.buscarPorId(id);
        return ResponseEntity.ok(unidade);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Unidade unidade){
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


