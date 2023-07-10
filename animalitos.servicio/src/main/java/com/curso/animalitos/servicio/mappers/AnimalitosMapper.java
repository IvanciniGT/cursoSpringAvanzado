package com.curso.animalitos.servicio.mappers;

import com.curso.animalitos.entidades.Animalito;
import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;

public class AnimalitosMapper {
    public static DatosAnimalitoDTO animalito2DatosAnimalito(Animalito nuevoAnimalitoPersistido) {
        DatosAnimalitoDTO datosAnimalitoDTO = new DatosAnimalitoDTO();//<- nuevoAnimalitoPersistido
        datosAnimalitoDTO.setId(nuevoAnimalitoPersistido.getId());
        datosAnimalitoDTO.setNombre(nuevoAnimalitoPersistido.getNombre());
        datosAnimalitoDTO.setColor(nuevoAnimalitoPersistido.getColor());
        datosAnimalitoDTO.setEdad(nuevoAnimalitoPersistido.getEdad());
        datosAnimalitoDTO.setTipo(nuevoAnimalitoPersistido.getTipo());
        return datosAnimalitoDTO;
    }
/*
    public static Animalito datosNuevoAnimalito2Animalito(DatosNuevoAnimalitoDTO datosNuevoAnimalitoDTO) {
        Animalito nuevoAnimalito = new Animalito();//;<- datosNuevoAnimalitoDTO
        nuevoAnimalito.setNombre(datosNuevoAnimalitoDTO.getNombre());
        nuevoAnimalito.setColor(datosNuevoAnimalitoDTO.getColor());
        nuevoAnimalito.setEdad(datosNuevoAnimalitoDTO.getEdad());
        nuevoAnimalito.setTipo(datosNuevoAnimalitoDTO.getTipo());
        return nuevoAnimalito;
    }
    */
    /*
    *
    * Animalitos:
    *   name
    *   age
    *   type
    * */
}
