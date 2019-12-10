package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.RelatorioClienteDto;
import br.com.tt.petshop.model.projection.AnimalProjection;
import br.com.tt.petshop.service.AnimalService;
import br.com.tt.petshop.service.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioEndpoint {
    private final RelatorioService relatorioService;
    private final AnimalService animalService;

    public RelatorioEndpoint(RelatorioService relatorioService, AnimalService animalService){
        this.relatorioService = relatorioService;
        this.animalService = animalService;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<RelatorioClienteDto>> listarClientes(){
        return ResponseEntity.ok(relatorioService.listarClientes());
    }

    @GetMapping("animais")
    public List<AnimalProjection> listarAnimais(){
        return animalService.listarParaRelatorio();
    }
}
