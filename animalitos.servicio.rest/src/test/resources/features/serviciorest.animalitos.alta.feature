#language:es

Característica: Servicio REST de Alta de animalitos v1

    Esquema del escenario: Dar de alta un animalito con datos correctos
        Dado un objeto JSON,
            Y ese objeto json tiene por "nombre": "<nombre>"
            Y ese objeto json tiene por "edad": <edad>
            Y ese objeto json tiene por "tipo": "<tipo>"
            Y ese objeto json tiene por "color": "<color>"
        Cuando hacemos una petición por método "post" al servicio "/api/v1/animalitos", enviando el objeto JSON,
        Entonces se recibe una respuesta con código de respuesta "CREATED"
            Y la respuesta debe contener un objeto JSON,
            Y el objeto JSON de respuesta debe tener por "nombre": "<nombre>"
            Y el objeto JSON de respuesta debe tener por "edad": <edad>
            Y el objeto JSON de respuesta debe tener por "tipo": "<tipo>"
            Y el objeto JSON de respuesta debe tener por "color": "<color>"
            Y el objeto JSON de respuesta debe tener por "id" un número entero
            #Y se debe haber enviado un email a "subscritos@animalitos.fermin.com"
            #Y ese correo debe tener por asunto: "Nuevo animalito"
            #Y ese correo debe contener en el cuerpo: "<nombre>"
            #Y debe haberse creado un registro en la base de datos, dentro de la tabla "animalitos", con id "<id>"
            #Y ese registro debe tener por "nombre": "<nombre>"
            #Y ese registro debe tener por "edad": <edad>
            #Y ese registro debe tener por "tipo": "<tipo>"
            #Y ese registro debe tener por "color": "<color>"

        Ejemplos:

        | nombre  | edad | tipo  | color   |
        | Pepito  | 2    | perro | marrón  |
        | Pepita  | 3    | gato  | blanco  |
        | Pepote  | 5    | papagayo | multicolor |

    Escenario: Dar de alta un animalito sin nombre, que es obligatorio
        Dado un objeto JSON,
            Y ese objeto json tiene por "edad": 2
            Y ese objeto json tiene por "tipo": "perro"
            Y ese objeto json tiene por "color": "marrón"
        Cuando hacemos una petición por método "post" al servicio "/api/v1/animalitos", enviando el objeto JSON,
        Entonces se recibe una respuesta con código de respuesta "BAD REQUEST"
            Y la respuesta debe estar vacía.
    
    Escenario: Dar de alta un animalito sin tipo, que es obligatorio
        Dado un objeto JSON,
            Y ese objeto json tiene por "nombre": "Pepito"
            Y ese objeto json tiene por "edad": 2
            Y ese objeto json tiene por "color": "marrón"
        Cuando hacemos una petición por método "post" al servicio "/api/v1/animalitos", enviando el objeto JSON,
        Entonces se recibe una respuesta con código de respuesta "BAD REQUEST"
            Y la respuesta debe estar vacía.

    Escenario: Dar de alta un animalito sin color, que es obligatorio
        Dado un objeto JSON,
            Y ese objeto json tiene por "nombre": "Pepito"
            Y ese objeto json tiene por "edad": 3
            Y ese objeto json tiene por "tipo": "chimpace"
        Cuando hacemos una petición por método "post" al servicio "/api/v1/animalitos", enviando el objeto JSON,
        Entonces se recibe una respuesta con código de respuesta "BAD REQUEST"
            Y la respuesta debe estar vacía.
