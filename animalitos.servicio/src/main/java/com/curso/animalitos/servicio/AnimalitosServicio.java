package com.curso.animalitos.servicio;

import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;

public interface AnimalitosServicio {

    DatosAnimalitoDTO altaAnimalito(DatosNuevoAnimalitoDTO datosNuevoAnimalitoDTO);
    DatosAnimalitoDTO recuperarAnimalito(Long id);

}
