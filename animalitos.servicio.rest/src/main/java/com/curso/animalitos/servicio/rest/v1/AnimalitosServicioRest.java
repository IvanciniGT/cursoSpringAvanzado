package com.curso.animalitos.servicio.rest.v1;


import com.curso.animalitos.servicio.AnimalitosServicio;
import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;
import com.curso.animalitos.servicio.rest.v1.dtos.DatosAnimalitoRestDTO;
import com.curso.animalitos.servicio.rest.v1.dtos.DatosNuevoAnimalitoRestDTO;
import com.curso.animalitos.servicio.rest.v1.mappers.AnimalitosRestDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Spring: A estas funciones me las llaman por HTTP, apuntando a un endpoint
// http://miservidor.com:puerto/endpoint -> una función java de las de aquí
@RestController
@RequestMapping("/api/v1") // Prefijo para los endpoints de esta clase
public class AnimalitosServicioRest {

    private final AnimalitosServicio animalitosServicio;
    private final AnimalitosRestDTOMapper animalitosRestDTOMapper;

    public AnimalitosServicioRest(AnimalitosServicio animalitosServicio, AnimalitosRestDTOMapper animalitosRestDTOMapper) {
        this.animalitosServicio = animalitosServicio;
        this.animalitosRestDTOMapper = animalitosRestDTOMapper;
    }

    // ResponseEntity: Lo que antes era: HTTPResponse (HttpServletResponse)
    // POST
    // /animalitos
    @PostMapping("/animalitos")
    // A Esta función, cuando la llamen por HTTP le llegarán los datos del nuevo animalito. Cómo llegan por HTTP ? JSON
                                                        // En el cuerpo del request, llega un JSON, mapealo directamente a un objeto de tipo DatosNuevoAnimalitoRestDTO
    ResponseEntity<DatosAnimalitoRestDTO> altaAnimalito(@RequestBody DatosNuevoAnimalitoRestDTO datosNuevoAnimalitoRestDTO){
                                                                     // Es parte de mi api, es lo que define cómo es el JSON que debe llegarme
                  // Lo que voy a devolver es otro objeto JAVA... que será convertido directamente por Spring a JSON, para ser devuelto en el cuerpo del Response Http
                  // Esto también es parte del API
        // Mapeo de datos
        DatosNuevoAnimalitoDTO datosNuevoAnimalitoDTO = this.animalitosRestDTOMapper.datosNuevoAnimalitoRest2datosNuevoAnimalitoRestDTO(datosNuevoAnimalitoRestDTO);
        // LLamar al servicio
        try {
            DatosAnimalitoDTO datosAnimalitoDTO = this.animalitosServicio.altaAnimalito(datosNuevoAnimalitoDTO);
            DatosAnimalitoRestDTO datosAnimalitoRestDTO = this.animalitosRestDTOMapper.datosAnimalitoDTO2datosAnimalitoRestDTO(datosAnimalitoDTO);
            return new ResponseEntity<>(datosAnimalitoRestDTO, HttpStatus.CREATED);
        }catch (Exception e) {
            // Manejo de errores
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // GET
    @GetMapping("/animalitos/{id}") // El id es un dato variable
    ResponseEntity<DatosAnimalitoRestDTO> recuperarAnimalito(@PathVariable("id") Long id){
                                                             // Le pedimos a Spring que obtenga el valor {id} del endpoint para pasarselo a esta función como argumento
        // Mapear el dato que entra al correspondiente de la capa del servicio
        // llamar al servicio y caprturar su salida
        // Convertir la salida del servicio en el tipo de objeto que maneje en esta capa
        // Podrá haber aquí algunas validaciones... ALGUNAS !!!! No tantas.
        // La lógica de negocio está a nivel de la capa del servicio! no quiero reimplementarla aquñi... con pobles problemas en el mnto y evolutivos de la aplicación
        Optional<DatosAnimalitoDTO> datosAnimalitoDTO =this.animalitosServicio.recuperarAnimalito(id);
        if(datosAnimalitoDTO.isPresent()) {
            // Mapeo de datos
            DatosAnimalitoRestDTO datosAnimalitoRestDTO = this.animalitosRestDTOMapper.datosAnimalitoDTO2datosAnimalitoRestDTO(datosAnimalitoDTO.get());
            return new ResponseEntity<>(datosAnimalitoRestDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


/*
* API REST

* GET  /api/v1/animalitos -> Recuperar todos los animalitos
* POST /api/v1/animalitos -> Alta de un animalito

* GET  /api/v1/animalitos/{id} -> Recuperar un animalito
* PUT  /api/v1/animalitos/{id} -> Modificar un animalito
* DELETE /api/v1/animalitos/{id} -> Borrar un animalito

Back end a día de hoy defino /animalitos

Y montones de personas en otros proyectos empiezan a llamar a este endpoint...
lo que significa que en su código, están metiendo esa ruta

El problema es que si a futuro cambias esto, que puedes hacerlo, 500 personas te van a estar buscando con un AK47

Yo tengo hoy un servicio que devuelve: {"id": 17, "nombre: "Pepito", "tipo": "Perro", "color": "Marrón", "edad": 3}
Mañana quiero hacer un cambio al servicio que devuelve: {"id": 17, "name: "Pepito", "type": "Perro", "color": "Marrón", "age": 3}
Pero te tienes que asegurar que esto no afecte a los que ya están usando la versión anterior.

Este cambio, es un BREAKING CHANGE... ya que si alguien está usando la versión anterior, y no cambia su código, no va a funcionar .
Esto implica que debo subir MAJOR VERSION

He de crear un api paralelo al que ya tenía que aporte esta funcionalidad... pero el anterior debo mantenerlo.



CIRR01
  ID
  Name
  Address
  Phone

PL/SQL

servicio JAVA
    (llamo al programa del BEND, que opera sobre esa tabla y hace muchas otras cositas...)
        Y Me devuelve:
           LISTA DE CLIENTES  (ID, Name, Address, Phone)

exposición de este servicio mediante rest

    /api/v1/clientes
        llama al Bend de JAVA (ID, Name, Address, Phone) y transforma la LISTA DE CLIENTES  (ID, nombre, direccion) en un JSON y lo devuelve al que lo ha pedido
        Además de esto, voy a compatibilizar el antiguo

    /api/v2/clientes
        llama al Bend de JAVA y transforma la LISTA DE CLIENTES  (ID, Name, Address, Phone) en un JSON y lo devuelve al que lo ha pedido
        Esta exposición del servicio es algo nuevo que debo desarrollar, que antes no existía



Si es correcto, llamas a la validación 2 veces... ya que en el servicio vas a tener la validación si o si.

Tengo una BBDD con la tabla personas... y tengo el campo DNI        * ESTA ES LA QUE REALMENTE IMPORTA
Y tengo una entidad Personas
Y tengo un servicio de persoans
Y tengo un API de personas expuesto por http                                * AQUI QUIERO VALIDACION
Y tengo un componente Angular que permite dar de alta una nueva persona     * AQUI QUIERO VALIDACION

Dónde valido el DNI?  Si solo tengo un sitio disponible: en la BBDD

*/

