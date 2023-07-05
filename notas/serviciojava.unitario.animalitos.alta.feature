#language:es
# Unitaria: Tengo que aislar al servicio de la BBDD y del servicio de envío de emails
Característica: Servicio JAVA de Alta de animalitos

    Esquema del escenario: Dar de alta un animalito con datos correctos
        Dado un objeto DatosNuevoAnimalitoDTO,
            Y ese objeto tiene por "nombre": "<nombre>"
            Y ese objeto tiene por "edad": <edad>
            Y ese objeto tiene por "tipo": "<tipo>"
            Y ese objeto tiene por "color": "<color>"
            Y tengo un Stub de la BBDD que devuelve un Animalito con id: 19876754
            Y tengo un Spy del servicio de envío de emails
        Cuando llamamos a la función "altaDeAnimalito" del servicio de animalitos, enviando el objeto DatosNuevoAnimalitoDTO
        Entonces se recibe un objeto de tipo DatosAnimalitoDTO,
            Y ese objeto debe tener por "nombre": "<nombre>"
            Y ese objeto debe tener por "edad": <edad>
            Y ese objeto debe tener por "tipo": "<tipo>"
            Y ese objeto debe tener por "color": "<color>"
            Y ese objeto debe tener por "id" 19876754
            Y se debe haber invocado a la función "enviarEmail" del spy
            Y se debe haber pasado como argumento "destinatario" a esa función: "subscritos@animalitos.fermin.com"
            Y se debe haber pasado como argumento "asunto" a esa función: "Nuevo animalito"
            Y se debe haber pasado como argumento "cuerpo" a esa función, un texto que contenga: "<nombre>"

        Ejemplos:

        | nombre  | edad | tipo  | color   |
        | Pepito  | 2    | perro | marrón  |
        | Pepita  | 3    | gato  | blanco  |
        | Pepote  | 5    | papagayo | multicolor |