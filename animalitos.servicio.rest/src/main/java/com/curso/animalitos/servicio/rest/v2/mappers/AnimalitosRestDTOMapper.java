package com.curso.animalitos.servicio.rest.v2.mappers;

import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;
import com.curso.animalitos.servicio.rest.v2.dtos.DatosAnimalitoRestDTO;
import com.curso.animalitos.servicio.rest.v2.dtos.DatosNuevoAnimalitoRestDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AnimalitosRestDTOMapper {

    DatosNuevoAnimalitoDTO datosNuevoAnimalitoRest2datosNuevoAnimalitoRestDTO(DatosNuevoAnimalitoRestDTO datosNuevoAnimalitoDTO);
    DatosAnimalitoRestDTO datosAnimalitoDTO2datosAnimalitoRestDTO(DatosAnimalitoDTO datosAnimalitoDTO);
}
