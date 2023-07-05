package com.curso.animalitos.servicio;

import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;

import java.util.Optional;

public interface AnimalitosServicio {

    DatosAnimalitoDTO altaAnimalito(DatosNuevoAnimalitoDTO datosNuevoAnimalitoDTO);
    Optional<DatosAnimalitoDTO> recuperarAnimalito(Long id);

}
