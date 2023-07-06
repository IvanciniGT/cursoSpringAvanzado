package com.curso.animalitos.servicio;

import com.curso.animalitos.entidades.Animalito;
import com.curso.animalitos.entidades.RepositorioAnimalitos;
import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;
import com.curso.animalitos.servicio.impl.AnimalitosServicioImpl;
import com.curso.animalitos.servicio.testdoubles.EmailsServicioMock;
import com.curso.animalitos.servicio.testdoubles.EmailsServicioSpy;
import com.curso.emails.servicio.EmailsServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("unit-testing-con-servicio-emails")
//@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AplicacionParaPruebas.class})
public class AnimalitosServicioUnitTest {

    // Generar un repositorio de animalitos de mentira
    // Que conteste con datos de mentira cuando lo invoco.
    // Montar un Stub - Sin ayuda de mockito
    // Montar un fake - Sin ayuda de Mockito
    // Montar un Stub - Con mockito (aunque mockito... le llamará Mock... pero en realidad será un Stub)

    @Mock
    private RepositorioAnimalitos repositorioDeAnimalitosMock ;
    @InjectMocks
    private final AnimalitosServicio servicioDeAnimalitos;
    private final EmailsServicio emailsServicio;

/*
    private RepositorioAnimalitos repositorioDeAnimalitos2 ;
    private AnimalitosServicio servicioDeAnimalitos2;

    @BeforeEach
    void inicializarRepositorio(){
        // O incluso dentro de la prueba concreta
        repositorioDeAnimalitos2 = Mockito.mock(RepositorioAnimalitos.class);
        servicioDeAnimalitos2 = new AnimalitosServicioImpl(repositorioDeAnimalitos2, emailsServicio);
    }
    */
    @Autowired
    public AnimalitosServicioUnitTest(EmailsServicio emailsServicio,
                                      AnimalitosServicio servicioDeAnimalitos
                                      /*RepositorioAnimalitos repositorioDeAnimalitos*/){ // Inyección de dependencias
        //this.repositorioDeAnimalitos = repositorioDeAnimalitos;
        this.servicioDeAnimalitos = servicioDeAnimalitos;
        this.emailsServicio = emailsServicio;
    }

    @Test
    public void altaAnimalitoConDatosOK(){
        // Para hacer una prueba unitaria, qué necesito?
        // Aislar al servicio del repositorio y del servicio de emails

        // Preparar datos para la prueba
        DatosNuevoAnimalitoDTO nuevosDatos = new DatosNuevoAnimalitoDTO();
        nuevosDatos.setNombre("Pepito");
        nuevosDatos.setTipo("Perro");
        nuevosDatos.setColor("Marrón");
        nuevosDatos.setEdad(3);
        // Configuro el mock
        // Estate pendiente... que te van a llamar... y con estos datos... al menos
        // Esos datos son con lo que deberían llamarte... PRUEBALO, ASEGURATE, cuando te llamen
        ((EmailsServicioMock)emailsServicio).configure(
                "subscriptores@animalitos.fermin.com",
                "Nuevo animalito",
                "Pepito");
        // Configurar el mock del repositorio de animalitos
        Long idQueSeDevuelve = 1234L;
        Animalito aDevolverPorElRepo = new Animalito();
        aDevolverPorElRepo.setId(idQueSeDevuelve);
        aDevolverPorElRepo.setNombre(nuevosDatos.getNombre());
        aDevolverPorElRepo.setTipo(nuevosDatos.getTipo());
        aDevolverPorElRepo.setColor(nuevosDatos.getColor());
        aDevolverPorElRepo.setEdad(nuevosDatos.getEdad());

        when(repositorioDeAnimalitosMock.save(any(Animalito.class))).thenReturn(
                aDevolverPorElRepo
        ) ;

        // Ejecutar el método que quiero probar
        DatosAnimalitoDTO nuevoAnimalito = servicioDeAnimalitos.altaAnimalito(nuevosDatos);
        // Esta llamada de arriba es la que envía el correo

        // Verificar que el resultado es el esperado
        Assertions.assertEquals(idQueSeDevuelve,nuevoAnimalito.getId());
        Assertions.assertEquals("Pepito", nuevoAnimalito.getNombre());
        Assertions.assertEquals("Perro", nuevoAnimalito.getTipo());
        Assertions.assertEquals("Marrón", nuevoAnimalito.getColor());
        Assertions.assertEquals(3, nuevoAnimalito.getEdad());
        // Verificar que el Servicio de animalitos ha llamado a la función enviarEmail del Servicio de Emails.
        // Assertions.assertEquals(1,((EmailsServicioSpy)emailsServicio).getNumeroDeLlamadas());
        // Verificarlo a través del mock
        ((EmailsServicioMock)emailsServicio).validate();
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
        Animalito animalitoExistente = repositorioDeAnimalitosMock.save(miAnimalito);
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
