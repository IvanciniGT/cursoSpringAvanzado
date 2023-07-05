package com.curso.animalitos.servicio.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

// Que en equals y en toString, se tengan en cuenta los campos heredados
@EqualsAndHashCode(callSuper = true)
@Data
public class DatosAnimalitoDTO extends DatosNuevoAnimalitoDTO{

    private Long id;

}
