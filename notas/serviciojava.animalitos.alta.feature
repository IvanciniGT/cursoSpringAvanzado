#language:es
# Integración: Comunicación entre el servicio y la BBDD y el envío de emails
Característica: Servicio JAVA de Alta de animalitos

    Esquema del escenario: Dar de alta un animalito con datos correctos
        Dado un objeto DatosNuevoAnimalitoDTO,
            Y ese objeto tiene por "nombre": "<nombre>"
            Y ese objeto tiene por "edad": <edad>
            Y ese objeto tiene por "tipo": "<tipo>"
            Y ese objeto tiene por "color": "<color>"
        Cuando hacemos una a la función "altaDeAnimalito" del servicio de animalitos, enviando el objeto DatosNuevoAnimalitoDTO
        Entonces se recibe un objeto de tipo DatosAnimalitoDTO,
            Y ese objeto debe tener por "nombre": "<nombre>"
            Y ese objeto debe tener por "edad": <edad>
            Y ese objeto debe tener por "tipo": "<tipo>"
            Y ese objeto debe tener por "color": "<color>"
            Y ese objeto debe tener por "id" un número entero
            Y se debe haber enviado un email a "subscritos@animalitos.fermin.com"
            Y ese correo debe tener por asunto: "Nuevo animalito"
            Y ese correo debe contener en el cuerpo: "<nombre>"
            Y debe haberse creado un registro en la base de datos, dentro de la tabla "animalitos", con id "<id>"
            Y ese registro debe tener por "nombre": "<nombre>"
            Y ese registro debe tener por "edad": <edad>
            Y ese registro debe tener por "tipo": "<tipo>"
            Y ese registro debe tener por "color": "<color>"

        Ejemplos:

        | nombre  | edad | tipo  | color   |
        | Pepito  | 2    | perro | marrón  |
        | Pepita  | 3    | gato  | blanco  |
        | Pepote  | 5    | papagayo | multicolor |