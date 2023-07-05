
Proyecto MAVEN: animalitos-entidades
    class Animalitos
    interface AnimalitosRepository... que spring auto. me implementa

    + Pruebas de esos ficheros y su lógica
    > animalito-entidades-v1-0-0.jar
   
Proyecto MAVEN: animalitos-servicios
    class     AnimalitosServicioImpl
    interface AnimalitosServicio

    + Pruebas de esos ficheros y su lógica
    > animalito-servicio-v1-0-0.jar
    DEPENDENCIA: animalitos-entidades

---


Componente A ----> Componente B
                   spy: Objetivo es saber que se ha invocado a la función del componente B
                   mock: Tendré un Spy más inteligente .. que es capaz per se , de saber si la llamada es correcta o no

Componente A ---- Componente B
             <---
                    stub: Objetivo es falsear unos datos muy concretos que alguien me debería enviar
                    fake: Un stub pero más inteligente.. con lógica. (RETUTILIZABLE)
