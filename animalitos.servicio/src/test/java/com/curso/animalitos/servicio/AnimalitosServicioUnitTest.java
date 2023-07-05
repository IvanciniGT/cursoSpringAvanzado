package com.curso.animalitos.servicio;

import com.curso.animalitos.entidades.Animalito;
import com.curso.animalitos.entidades.RepositorioAnimalitos;
import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AplicacionParaPruebas.class})
public class AnimalitosServicioUnitTest {

    // Generar un repositorio de animalitos de mentira
    // Que conteste con datos de mentira cuando lo invoco.
    // Montar un Stub - Sin ayuda de mockito
    // Montar un fake - Sin ayuda de Mockito
    // Montar un Stub - Con mockito (aunque mockito... le llamará Mock... pero en realidad será un Stub)

    private final RepositorioAnimalitos repositorioDeAnimalitos ;
    private final AnimalitosServicio servicioDeAnimalitos;

    @Autowired
    public AnimalitosServicioUnitTest(AnimalitosServicio servicioDeAnimalitos, RepositorioAnimalitos repositorioDeAnimalitos){ // Inyección de dependencias
        this.repositorioDeAnimalitos = repositorioDeAnimalitos;
        this.servicioDeAnimalitos = servicioDeAnimalitos;
    }

    @Test
    public void altaAnimalitoConDatosOK(){
        DatosNuevoAnimalitoDTO nuevosDatos = new DatosNuevoAnimalitoDTO();
        nuevosDatos.setNombre("Pepito");
        nuevosDatos.setTipo("Perro");
        nuevosDatos.setColor("Marrón");
        nuevosDatos.setEdad(3);
        DatosAnimalitoDTO nuevoAnimalito = servicioDeAnimalitos.altaAnimalito(nuevosDatos);
        Assertions.assertNotNull(nuevoAnimalito.getId());
        Assertions.assertEquals("Pepito", nuevoAnimalito.getNombre());
        Assertions.assertEquals("Perro", nuevoAnimalito.getTipo());
        Assertions.assertEquals("Marrón", nuevoAnimalito.getColor());
        Assertions.assertEquals(3, nuevoAnimalito.getEdad());
    }

    @Test
    public void altaAnimalitoSinNombre(){
        DatosNuevoAnimalitoDTO nuevosDatos = new DatosNuevoAnimalitoDTO();
        nuevosDatos.setTipo("Perro");
        nuevosDatos.setColor("Marrón");
        nuevosDatos.setEdad(3);
        Assertions.assertThrows( Exception.class,  () ->  servicioDeAnimalitos.altaAnimalito(nuevosDatos));
    }
    @Test
    public void recuperarAnimalitoQueExiste(){
        Animalito miAnimalito = new Animalito();
        miAnimalito.setNombre("Pepito");
        miAnimalito.setTipo("Perro");
        miAnimalito.setColor("Marrón");
        miAnimalito.setEdad(3);
        Animalito animalitoExistente = repositorioDeAnimalitos.save(miAnimalito);
        // ^ Me aseguro que tengo un animalito en el repo
        // v Trato de recuperar el animalito
        Optional<DatosAnimalitoDTO> animalitoRecuperado = servicioDeAnimalitos.recuperarAnimalito(animalitoExistente.getId());
        // Me aseguro que he sido capaz de recuperarlo con todos sus datos OK
        Assertions.assertTrue(animalitoRecuperado.isPresent());
        Assertions.assertEquals(animalitoExistente.getId(), animalitoRecuperado.get().getId());
        Assertions.assertEquals(animalitoExistente.getNombre(), animalitoRecuperado.get().getNombre());
        Assertions.assertEquals(animalitoExistente.getTipo(), animalitoRecuperado.get().getTipo());
        Assertions.assertEquals(animalitoExistente.getColor(), animalitoRecuperado.get().getColor());
        Assertions.assertEquals(animalitoExistente.getEdad(), animalitoRecuperado.get().getEdad());
    }
    @Test
    public void recuperarAnimalitoQueNoExiste(){
        Optional<DatosAnimalitoDTO> animalitoRecuperado = servicioDeAnimalitos.recuperarAnimalito(-1000L);
        // Me aseguro que he sido capaz de recuperarlo con todos sus datos OK
        Assertions.assertFalse(animalitoRecuperado.isPresent());
    }

}
