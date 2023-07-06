package com.curso.animalitos.servicio;

import com.curso.animalitos.entidades.Animalito;
import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.curso.animalitos.entidades.RepositorioAnimalitos;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AplicacionParaPruebas.class})
public class AnimalitosServicioSistemaTest {

    private final RepositorioAnimalitos repositorioDeAnimalitos ;
    private final AnimalitosServicio servicioDeAnimalitos;

    @Autowired
    public AnimalitosServicioSistemaTest(AnimalitosServicio servicioDeAnimalitos, RepositorioAnimalitos repositorioDeAnimalitos){ // Inyección de dependencias
        this.repositorioDeAnimalitos = repositorioDeAnimalitos;
        this.servicioDeAnimalitos = servicioDeAnimalitos;
    }

    @Test
    public void altaAnimalitoConDatosOK(){
        // Preparacion de los datos
        DatosNuevoAnimalitoDTO nuevosDatos = new DatosNuevoAnimalitoDTO();
        nuevosDatos.setNombre("Pepito");
        nuevosDatos.setTipo("Perro");
        nuevosDatos.setColor("Marrón");
        nuevosDatos.setEdad(3);
        // Acciones
        DatosAnimalitoDTO nuevoAnimalito = servicioDeAnimalitos.altaAnimalito(nuevosDatos);
        // Verificaciones
        // Verifico la respuesta del servicio
        Assertions.assertNotNull(nuevoAnimalito.getId());
        Assertions.assertEquals("Pepito", nuevoAnimalito.getNombre());
        Assertions.assertEquals("Perro", nuevoAnimalito.getTipo());
        Assertions.assertEquals("Marrón", nuevoAnimalito.getColor());
        Assertions.assertEquals(3, nuevoAnimalito.getEdad());


        // Verifico que el animalito se haya guardado en el repositorio
        // Aqui no pruebo el repositorio.... eso lo probé en el otro proyecto.
        // Esto es la prueba de integración: Está llamando el servicio al Repositorio???
        Animalito animalitoPersistido = repositorioDeAnimalitos.getById(nuevoAnimalito.getId());
        Assertions.assertNotNull(animalitoPersistido);
        Assertions.assertEquals("Pepito", animalitoPersistido.getNombre());
        Assertions.assertEquals("Perro", animalitoPersistido.getTipo());
        Assertions.assertEquals("Marrón", animalitoPersistido.getColor());
        Assertions.assertEquals(3, animalitoPersistido.getEdad());

        // Me tocaría crear un cliente pop3 o imap, conectar con esa cuenta de email... y verificar que se recibe el email
    }// BDD > TDD

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
