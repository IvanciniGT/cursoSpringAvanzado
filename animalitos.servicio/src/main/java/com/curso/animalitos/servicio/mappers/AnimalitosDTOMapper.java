package com.curso.animalitos.servicio.mappers;

import com.curso.animalitos.entidades.Animalito;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AnimalitosDTOMapper {

    //AnimalitosDTOMapper INSTANCE = Mappers.getMapper(AnimalitosDTOMapper.class);
    Animalito datosNuevoAnimalito2Animalito(DatosNuevoAnimalitoDTO datosNuevoAnimalitoDTO);

}
