package com.curso.animalitos.servicio.rest.v1.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

// Que en equals y en toString, se tengan en cuenta los campos heredados
@EqualsAndHashCode(callSuper = true)
@Data
public class DatosAnimalitoRestDTO extends DatosNuevoAnimalitoRestDTO {

    private Long id;

}
