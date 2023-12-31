package com.curso.animalitos.servicio.rest.v1.mappers;

import com.curso.animalitos.entidades.Animalito;
import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;
import com.curso.animalitos.servicio.rest.v1.dtos.DatosAnimalitoRestDTO;
import com.curso.animalitos.servicio.rest.v1.dtos.DatosNuevoAnimalitoRestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface AnimalitosRestDTOMapper {

    //@Mappings({
    //    @Mapping(source = "nombre", target = "name")
    //})
    DatosNuevoAnimalitoDTO datosNuevoAnimalitoRest2datosNuevoAnimalitoRestDTO(DatosNuevoAnimalitoRestDTO datosNuevoAnimalitoDTO);
    DatosAnimalitoRestDTO datosAnimalitoDTO2datosAnimalitoRestDTO(DatosAnimalitoDTO datosAnimalitoDTO);
}
