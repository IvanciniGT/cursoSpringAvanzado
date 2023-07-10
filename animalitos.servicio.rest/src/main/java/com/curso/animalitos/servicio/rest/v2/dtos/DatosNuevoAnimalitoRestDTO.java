package com.curso.animalitos.servicio.rest.v2.dtos;

import lombok.Data;

@Data
public class DatosNuevoAnimalitoRestDTO {

    private String nombre;
    private String tipo;
    private String color;
    private Integer edad;

}
