package com.curso.animalitos.servicio.dtos;

import lombok.Data;

@Data
public class DatosNuevoAnimalitoDTO {

    private String nombre;
    private String tipo;
    private String color;
    private Integer edad;

}
