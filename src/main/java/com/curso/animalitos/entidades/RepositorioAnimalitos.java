package com.curso.animalitos.entidades;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioAnimalitos extends JpaRepository<Animalito, Long> {
    // Este se encarga de las operaciones a la BBDD:
    // CRUD: Create, Read, Update, Delete

    // En automático, cuando arranque Spring, Spring creará una clase que implemente este interfaz
    // Y añadirá todos los métodos que hacen falta "básicos" para trabajar contra la BBDD
    /*
        List<Animalito> findByColor(String color); // Está función será también implementada por Spring
        List<Animalito> findByEdadIsNull(); // Está función será también implementada por Spring
        List<Animalito> findByEdadIsNotNull(); // Está función será también implementada por Spring
        List<Animalito> findByNombreStartingWith(String prefix); // Está función será también implementada por Spring
        List<Animalito> findByNombreEndingWith(String prefix); // Está función será también implementada por Spring
        List<Animalito> findByNombreContaining(String prefix); // Está función será también implementada por Spring
        List<Animalito> findByEdadLessThan(Integer minAge); // Está función será también implementada por Spring
                             //GreaterThan, GreaterThanEqual, LessThan, LessThanEqual, Between
        List<Animalito> findByTipoAndColor(String tipo, String color); // Está función será también implementada por Spring
                            //Or
    */
}
