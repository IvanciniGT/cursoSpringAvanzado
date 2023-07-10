package com.curso.animalitos.servicio.mappers;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalitosDTOMapperConfiguration {
    @Bean
    AnimalitosDTOMapper federico() { //configurarMapperAnimalitosDTO() {
        return Mappers.getMapper(AnimalitosDTOMapper.class);
    }
}
