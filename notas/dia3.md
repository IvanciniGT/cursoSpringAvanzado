
Proyecto MAVEN: animalitos-entidades
    class Animalitos
    interface AnimalitosRepository... que spring auto. me implementa

    + Pruebas de esos ficheros y su lógica
    > animalito-entidades-v2-0-0.jar
   
Proyecto MAVEN: animalitos-servicios
    class     AnimalitosServicioImpl
    interface AnimalitosServicio

    + Pruebas de esos ficheros y su lógica
    > animalito-servicio-v2-0-0.jar
    DEPENDENCIA: animalitos-entidades

---


Componente A ----> Componente B
                   spy: Objetivo es saber que se ha invocado a la función del componente B
                   mock: Tendré un Spy más inteligente .. que es capaz per se , de saber si la llamada es correcta o no

Componente A ---- Componente B
             <---
                    stub: Objetivo es falsear unos datos muy concretos que alguien me debería enviar
                    fake: Un stub pero más inteligente.. con lógica. (RETUTILIZABLE)
                    Si el fake lo llevo al extremo, acaba siendo mi implementaciuón real!

----


ServicioAnimalitosImpl <<< ESTE ES EL OBJETO DE PRUEBA. NO QUIERO AISLARME DE ESTO... COÑO ESTO LO QUE PRUEBAO !!!

Me tendré que esperar a que esté desarrollado el servicio, para ejecutar la prueba, no para definirla!
ESTO ES LO QUE OCURRE EN CUALQUIER INDUSTRIA DEL MUNDO 
LO QUE NO QUIERO ESPERAR ES QUE SE HAYA DESARROLLADO EL SERVICIO DE EMAILS o EL REPOSITORIO... que es se lo han encargado a otro equipo...
y no voy a estar yo de brazo cruzados hasta que los otros acaben


Requerimientos del servicio de animalitos:
- Dar de alta el animalito en el repo                   ****
- Trincar el id,
- Mandar un correo                                      ****
- Devolver los datos del animalito con el ID.

Y yo me tengo que meter a desarrollar esto, sin que tenga un servicio para mandar emails YA DESARROLLADO
Y yo me tengo que meter a desarrollar esto sin que tenga un repositorio YA DESARROLLADO
QUE ESTAS COSAS LAS HACE OTRO EQUIPO !!!!


PRUEBA:
 Configurar unos datos para pedir el alta de un animalito
 Servicio de emails de mentirijilla <<< MOCK   

  Requerimientos del servicio de animalitos:
    - Dar de alta el animalito en el repo                   ****
    - Trincar el id,
    - Mandar un correo                                      ****
    - Devolver los datos del animalito con el ID.

  Valido:
   - Miro que mande ID
   - Miro que haya llamado al servicio de emails con los datos adecuados
   - Miro que se haya llamado al repo con los datos adecuados



