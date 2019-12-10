package br.com.tt.petshop.service;

import br.com.tt.petshop.dto.AnimalInDto;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.model.projection.AnimalProjection;
import br.com.tt.petshop.repository.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.omg.PortableServer.POAPackage.AdapterAlreadyExistsHelper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final ModelMapper mapper;
    private final ClienteService clienteService;
    private final UnidadeService unidadeService;

    public AnimalService(AnimalRepository animalRepository, ModelMapper mapper, ClienteService clienteService, UnidadeService unidadeService) {
        this.animalRepository = animalRepository;
        this.mapper = mapper;
        this.clienteService = clienteService;
        this.unidadeService = unidadeService;
    }

    public List<Animal> listar(Optional<String> nome, Optional<Long> idCliente, Optional<Long> idUnidade) {
        Animal animal = new Animal();
        if (nome.isPresent()){
            animal.setNome(nome.get());
        }
        if (idCliente.isPresent()){
            Cliente cliente = new Cliente();
            cliente.setId(idCliente.get());
            animal.setCliente(cliente);
        }
        if (idUnidade.isPresent()){
            Unidade unidade = new Unidade();
            unidade.setId(idUnidade.get());
            animal.setUnidade(unidade);
        }

        return animalRepository.findAll(Example.of(animal));
    }

    public Animal salvar(AnimalInDto animalInDto){
        Animal animal = mapper.map(animalInDto, Animal.class);
        animal.setCliente(obterClienteAnimal(animalInDto.getCliente()));
        animal.setUnidade(obterUnidadeAnimal(animalInDto.getUnidade()));
        return animalRepository.save(animal);
    }

    private Cliente obterClienteAnimal(Long idCliente) {
        return clienteService.buscarPorId(idCliente);
    }

    private Unidade obterUnidadeAnimal(Long unidade) {
        return unidadeService.buscarPorId(unidade);
    }

    public List<AnimalProjection> listarParaRelatorio (){
        return animalRepository.findAllOrderByNome();
    }
}