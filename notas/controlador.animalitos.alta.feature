
# Unitaria. Para ello necesito:
# Aislar al controlador del servicio 
Característica: Controlador REST de Alta de animalitos 

    Esquema del escenario: Dar de alta un animalito con datos correctos
        Dado un objeto JSON,
            Y ese objeto json tiene por "nombre": "<nombre>"
            Y ese objeto json tiene por "edad": <edad>
            Y ese objeto json tiene por "tipo": "<tipo>"
            Y ese objeto json tiene por "color": "<color>"
            Y un stub del servicio de animalitos que devuelve un animalito con los mismo datos e id: 1
        Cuando hacemos una petición por método "post" al servicio "/api/v1/animalitos", enviando el objeto JSON,
        Entonces se recibe una respuesta con código de respuesta "CREATED"
            Y la respuesta debe contener un objeto JSON,
            Y el objeto JSON de respuesta debe tener por "nombre": "<nombre>"
            Y el objeto JSON de respuesta debe tener por "edad": <edad>
            Y el objeto JSON de respuesta debe tener por "tipo": "<tipo>"
            Y el objeto JSON de respuesta debe tener por "color": "<color>"
            Y el objeto JSON de respuesta debe tener por "id" 1

        Ejemplos:

        | nombre  | edad | tipo  | color   |
        | Pepito  | 2    | perro | marrón  |
        | Pepita  | 3    | gato  | blanco  |
        | Pepote  | 5    | papagayo | multicolor |