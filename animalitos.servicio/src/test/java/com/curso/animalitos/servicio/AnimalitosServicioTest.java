package com.curso.animalitos.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.curso.animalitos.entidades.RepositorioAnimalitos;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AplicacionParaPruebas.class})
public class AnimalitosServicioTest {

    private final RepositorioAnimalitos repositorioDeAnimalitos ;

    @Autowired
    public AnimalitosServicioTest (RepositorioAnimalitos repositorioDeAnimalitos){ // Inyección de dependencias
        this.repositorioDeAnimalitos = repositorioDeAnimalitos;
    }

}
