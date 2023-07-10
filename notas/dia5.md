
animalitos-entidades.jar
    ^
animalitos-servicio-api.jar              servicio-emails-api.jar
   ^                 ^                    ^                   ^    
   ^             animalitos-servicio-impl.jar               servicio-emails-impl.jar
   ^                                     ^                      ^
animalitos-servicio-rest-api.jar         (1)                    ^       
                     ^                                          ^
                 animalitos-servicio-rest-impl.jar              ^     (1)   driverBBDD
                                    ^                           ^      ^     ^
                                    ________________________________________________
                                                 aplicacion   (Consolida lo demás... configuro que componentes quiero)

Este es el reflejo de la INVERSION DE DEPENDENCIAS de la que tanto hemos hablado

COMPONENTES QUE NO DEPENDEN DE IMPLEMENTACIONES, SINO DE INTERFACES !