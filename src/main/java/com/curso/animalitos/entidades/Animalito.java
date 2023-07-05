package com.curso.animalitos.entidades;

import lombok.Data;

import javax.persistence.*;

@Data   // Añade getter, setter a todos los campos y .equals, .toString ...
@Entity // Me permite relacionar esta clase con una tabla en una BBDD , para su persistencia
@Table (
    name = "animales" // Nombre de la tabla en la BBDD
)
public class Animalito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false, length = 50, nullable = false)
    private String nombre;

    @Column(updatable = false, length = 50, nullable = false)
    private String tipo;

    @Column(updatable = false, length = 50, nullable = false)
    private String color;

    @Column(updatable = true, nullable = true)
    private Integer edad;
}
