package com.curso.animalitos.entidades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Oye JUnit, que este fichero (clase), no quiero que le asignes parametros tu... que esto es de Spring.
@ExtendWith(SpringExtension.class)
// Esta anotación arranca una aplciación de Spring para hacerle pruebas
// Esa aplicación es la que levanta un repositorio para trabajar con animalitos.
// Necesita una BBDD... usaremos una bbdd H2 en memoria apra las pruebas
@SpringBootTest(classes = {AplicacionParaPruebas.class})
public class AnimalitosEntidadTest {

    private final RepositorioAnimalitos repositorioDeAnimalitos ;

    // Oye JUnit, que es Spring el que debe proporcionar valor para el repositorio de animalitos, no tu.
    @Autowired
    public AnimalitosEntidadTest (RepositorioAnimalitos repositorioDeAnimalitos){ // Inyección de dependencias
        this.repositorioDeAnimalitos = repositorioDeAnimalitos;
    }

    // Funciones de prueba con la anotación @Test
    @Test
    public void altaDeAnimalitoConDatosOK(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setTipo("Perro");
        miAnimalito.setColor("Marrón");
        miAnimalito.setEdad(3);
        Assertions.assertNull(miAnimalito.getId());

        Animalito animalitoGuardado = repositorioDeAnimalitos.save(miAnimalito);

        Assertions.assertNotNull(animalitoGuardado.getId());
    }

}
