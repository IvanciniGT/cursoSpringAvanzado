package com.curso.animalitos.entidades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
        Assertions.assertEquals("Pepito", animalitoGuardado.getNombre());
        Assertions.assertEquals("Perro", animalitoGuardado.getTipo());
        Assertions.assertEquals("Marrón", animalitoGuardado.getColor());
        Assertions.assertEquals(3, animalitoGuardado.getEdad());
    }
    @Test
    public void altaDeAnimalitoSinEdad(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setTipo("Perro");
        miAnimalito.setColor("Marrón");
        Assertions.assertNull(miAnimalito.getId());

        Animalito animalitoGuardado = repositorioDeAnimalitos.save(miAnimalito);

        Assertions.assertNotNull(animalitoGuardado.getId());
        Assertions.assertEquals("Pepito", animalitoGuardado.getNombre());
        Assertions.assertEquals("Perro", animalitoGuardado.getTipo());
        Assertions.assertEquals("Marrón", animalitoGuardado.getColor());
        Assertions.assertNull(animalitoGuardado.getEdad());
    }
    @Test
    public void altaDeAnimalitoSinNombre(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setTipo("Perro");
        miAnimalito.setColor("Marrón");
        miAnimalito.setEdad(3);
        Assertions.assertThrows(Exception.class, () -> repositorioDeAnimalitos.save(miAnimalito) ); // Programación funcional
    }
    @Test
    @DisplayName("Alta de animalito sin tipo, que es obligatorio, y no debería permitirse")
    public void altaDeAnimalitoSinTipo(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setColor("Marrón");
        miAnimalito.setEdad(3);
        Assertions.assertThrows(Exception.class, () -> repositorioDeAnimalitos.save(miAnimalito) ); // Programación funcional
    }
    @Test
    @DisplayName("Alta de animalito sin color, que es obligatorio, y no debería permitirse")
    public void altaDeAnimalitoSinColor(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setTipo("Perro");
        miAnimalito.setEdad(3);
        Assertions.assertThrows(Exception.class, () -> repositorioDeAnimalitos.save(miAnimalito) ); // Programación funcional
    }
    @Test
    public void altaDeAnimalitoConNombreMuyLargo(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("aaklsjdhkljhds fkjhfdakljhjkdlsfhksdjaf hkjadhsf kl");
        miAnimalito.setColor("Marrón");
        miAnimalito.setTipo("Perro");
        miAnimalito.setEdad(3);
        Assertions.assertThrows(Exception.class, () -> repositorioDeAnimalitos.save(miAnimalito) ); // Programación funcional
    }
    @Test
    public void altaDeAnimalitoConTipoMuyLargo(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setTipo("aaklsjdhkljhds fkjhfdakljhjkdlsfhksdjaf hkjadhsf kl");
        miAnimalito.setColor("Marrón");
        miAnimalito.setEdad(3);
        Assertions.assertThrows(Exception.class, () -> repositorioDeAnimalitos.save(miAnimalito) ); // Programación funcional
    }
    @Test
    public void altaDeAnimalitoConColorMuyLargo (){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setTipo("Perro");
        miAnimalito.setColor("aaklsjdhkljhds fkjhfdakljhjkdlsfhksdjaf hkjadhsf kl");
        miAnimalito.setEdad(3);
        Assertions.assertThrows(Exception.class, () -> repositorioDeAnimalitos.save(miAnimalito) ); // Programación funcional
    }
/*
    @Test
    @DisplayName("Dar de alta un animalito con sus datos ok.. y querer cambiarle luego el nombre... Esto no debería permitirse")
    public void modificarNombreDeAnimalito(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setTipo("Perro");
        miAnimalito.setColor("Marrón");
        miAnimalito.setEdad(3);

        Animalito animalitoGuardado = repositorioDeAnimalitos.save(miAnimalito);

        animalitoGuardado.setNombre("Pepita");
        Assertions.assertThrows(Exception.class, () -> repositorioDeAnimalitos.save(animalitoGuardado) ); // Programación funcional

    }

    @Test
    @DisplayName("Dar de alta un animalito con sus datos ok.. y querer cambiarle luego el tipo... Esto no debería permitirse")
    public void modificarTipoDeAnimalito(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setTipo("Perro");
        miAnimalito.setColor("Marrón");
        miAnimalito.setEdad(3);

        Animalito animalitoGuardado = repositorioDeAnimalitos.save(miAnimalito);

        animalitoGuardado.setTipo("Gato");
        Assertions.assertThrows(Exception.class, () -> repositorioDeAnimalitos.save(animalitoGuardado) ); // Programación funcional
    }

    @Test
    @DisplayName("Dar de alta un animalito con sus datos ok.. y querer cambiarle luego el color... Esto no debería permitirse")
    public void modificarColorDeAnimalito(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setTipo("Perro");
        miAnimalito.setColor("Marrón");
        miAnimalito.setEdad(3);

        Animalito animalitoGuardado = repositorioDeAnimalitos.save(miAnimalito);

        miAnimalito.setColor("Violeta");
        Assertions.assertThrows(Exception.class, () -> repositorioDeAnimalitos.save(animalitoGuardado) ); // Programación funcional

    }

 */
}
