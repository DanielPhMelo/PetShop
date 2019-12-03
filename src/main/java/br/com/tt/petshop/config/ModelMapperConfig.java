package br.com.tt.petshop.config;

import br.com.tt.petshop.dto.AnimalOutDto;
import br.com.tt.petshop.model.Animal;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Animal.class, AnimalOutDto.class)
                .addMapping(
                        (animal)-> animal.getUnidade().getNome(),
                        AnimalOutDto::setUnidade)
                .addMapping(
                        (animal)->animal.getCliente().getNome(),
                        AnimalOutDto::setCliente);
        return modelMapper;
    }

}
