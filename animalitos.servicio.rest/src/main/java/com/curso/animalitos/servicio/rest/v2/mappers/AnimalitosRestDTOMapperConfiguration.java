package com.curso.animalitos.servicio.rest.v2.mappers;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalitosRestDTOMapperConfiguration {
    @Bean
    AnimalitosRestDTOMapper configurarElMapeadorDeEstaCapa() { //configurarMapperAnimalitosDTO() {
        return Mappers.getMapper(AnimalitosRestDTOMapper.class);
    }
}
