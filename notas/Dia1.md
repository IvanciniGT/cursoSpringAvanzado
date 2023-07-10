
# Spring

Framework de inversión de control

## Inversión de control?

Delegamos el control del flujo del programa al framework.

Y para qué?
- Montar sistemas modulares con bajo nivel de acoplamiento entre los módulos que los componen  -> En contra de los sistemas monolíticos
  - Facilitar el mantenimiento y los evolutivos de un sistema. Que el sistema (o algún componente) fácilmente reemplazable
- Olvidarnos de la gestión del flujo
- Picar menos

La principal ventaja de la inversión de control, es la inyección de dependencias.

## Inyección de dependencias

Patrón de diseño en poo, mediante el cúal hacemos que una clase no cree instancias de un tipo de datos, sino que le sean suministradas (inyectadas) desde fuera.

Su misión principal es asegurar la inversión de dependencias.

## El tio bob. Robert Cecile Martin

## Principio de inversión de dependencias

Un componente de alto nivel de un sistema no debería depender nunca de implementaciones de otros módulos de más bajo nivel... sino de abstracciones (contratos, interfaces).

---

Quiero montar una aplicación... de terminal (consola)
Que permita a un usuario introducir una palabra y un idioma.
Y si la palabra existe queremos que le muestra los diferentes significados.
Si no existe, que le muestre alternativas.

- Proveedor de palabras / idiomas                           java                Backend
    ^ v API (Application programming Interface)             interfaces java
- Aplicación de consola                                     java                Frontend

    interface Diccionario {
        String getIdioma();
        boolean existe(String palabra);
        //List<String> getSignificados(String palabra);
            // Esto es una mierda gigante. Me explicais el comportamiento de esa función? 
            // Qué pasa si una palabra no existe "manana"?
            // - null (discriminativo)          \   Ninguna es explicita. Para saber cómo se comporta tengo que ir: CODIGO / DOCU
            // - lista vacía                    /
            // - NoSuchWordException   Es explicita.. pero uso Excepciones (que es un recurso muy caro) para controlar lógica de la app.
            //                         Me permite discriminar claramente entre 2 situaciones muy diferentes: EXISTE LA PALABRA o NO

        Optional<List<String>> getSignificados(String palabra) // Es la única opción válida desde Java 1.8
            // optional.isPresent()        optional.isEmpty()
        List<String> getAlternativas(String palabra);
                                            // manana    banana, mañana, manzana, manzano
                                            // mañana      mañana, banana
                                            // $$(764kktua) no he encontrado ninguna
    }
    interface SuministradorDeDiccionarios{
        boolean tienesDiccionarioDe(String idioma);
        Optional<Diccionario> getDiccionario(String idioma);

            Dado que tengo un SuministradorDeDiccionario
                    Suministrador suministrador; // Que me lo inyecten 
                Y conozco de antemano que el suministrador tiene un diccionario del "Idioma de los elfos"
                    suministrador.tienesDiccionarioDe("Idioma de los elfos") devuelve true
            Cuando le pido al suministrador un diccionario del "Idioma de los elfos"
                    Optional<Diccionario> diccionario = suministrador.getDiccionario("Idioma de los elfos")
            Entonces el suministrador me devuelve un Optional, cargadito con un diccionario
                    AssertNotNull ( diccionario )
                    AssertTrue( diccionario.isPresent() )
                Y el diccionario que viene es de el "Idioma de los elfos"
                    AssertEquals ( "Idioma de los elfos", diccionario.get().getIdioma() )

    }

---
El concepto de un ASSERT es una aseguración que tu quieres establecer para continuar.
Eso existe en JAVA pelao
assert()

En pruebas, usamos los asserts como validaciones de que la prueba se cumple (CONDICIONES)
JUnit tiene una clase llamada:
    - Junit4: Asserts
    - Junit5: Assertions
Esa clase tiene funciones estáticas que te permiten hacer comprobaciones. En caso que alguna comprobación no sea correcta, se invoca al método: fail() de JUnit, y la prueba se establece como fallida.
---
```java
    // SuministradorDeDiccionariosDesdeFichero
    // SuministradorDeDiccionariosDesdeWebService
    class SuministradorDeDiccionariosDesdeBBDD implements SuministradorDeDiccionarios{
        @Override
        public boolean tienesDiccionarioDe(String idioma){
            //...
        }
        @Override
        public Optional<Diccionario> getDiccionario(String idioma){
            //...
        }
    }

    class DiccionarioDesdeBBDD implements Diccionario{
        @Override
        public String getIdioma(){
            //...
        }
        @Override
        public boolean existe(String palabra){
            //...
        }
        @Override
        public Optional<List<String>> getSignificados(String palabra){
            //...
        }
        @Override
        public List<String> getAlternativas(String palabra){
            //...
        }
    }

    interface SuministradorDeDiccionariosFactory{ // Desde Java 1.8 se pueden meter funciones estáticas públicas
                                                    // Desde java 1.9, también privadas
        public static SuministradorDeDiccionarios dameSuministrador(){
            return new SuministradorDeDiccionariosDesdeBBDD();
        }
    }
```
---
App.java

    import com.curso.Diccionario;
    import com.curso.SuministradorDeDiccionarios;
    import com.curso.SuministradorDeDiccionariosFactory;
    //import com.curso.SuministradorDeDiccionariosDesdeBBDD; // Esta es la perdición !!!!  
                                                            // Para evitar esa linea de código existe Spring
                                                            // Este linea de código se está meando en EL PRINCIPIO DE INVERSIÓN DE DEPENDENCIAS
    public class App {

        public void main ....{

        }

        private static void procesarPeticion(String palabra, String idioma, SuministradorDeDiccionarios suministradorDeDiccionarios){ // Inyección de dependencias
            //...
            //SuministradorDeDiccionarios suministradorDeDiccionarios = new SuministradorDeDiccionariosDesdeBBDD();
            //SuministradorDeDiccionarios suministradorDeDiccionarios = SuministradorDeDiccionariosFactory.dameSuministrador();
            //Iterable<SuministradorDeDiccionarios> suministradores = ServiceLoader.load(SuministradorDeDiccionarios.class)
            if(suministradorDeDiccionarios.tienesDiccionarioDe(idioma)){
                Diccionario diccionario = suministradorDeDiccionarios.getDiccionario(idioma);
                if(diccionario.existe(palabra)){
                    System.out.println("Significados de " + palabra);
                    diccionario.getSignificados(palabra).forEach(System.out::println);
                }else{
                    System.out.println("No existe " + palabra);
                    diccionario.getAlternativas(palabra).forEach(System.out::println);
                }
            }else{
                System.out.println("No tengo diccionario de " + idioma);
            }
            //...
        }

    }


---

Sonarqube:
- Complejidad ciclomática:
    Número de caminos que puede tomar un código (potencialmente)
        // tarea 1
        if(condicion1):
            // tarea 2
        else if (condicion2) 
            // tarea 3

---
## Paradigmas de Programación

- Programación imperativa               Cuando voy dando órdenes que se ejecutan de forma secuencial
                                        Si quiero romper la secuencialidad, uso: if, for, while, switch, break, continue
- Programación procedural               Cuando el lenguaje me da la posibilidad de crear funciones con código reutilizables : JAVA
                                        (que no son más que un conjunto de instrucciones que se ejecutan de forma secuencial)
                                        Y además me da la opción de invocar a esas funciones
- Programación funcional                Cuando el lenguaje me permite referenciar desde una variable a una función.
                                        Y ejecutar esa función a través de la variable.
                                        El concepto es simple... La cuestión es el impacto de esto.
                                            Puedo pasar funciones como parámetros de otras funciones
                                            Puedo montar una función que devuelva otra función
- Programación Orientada a Objetos      Cuando el lenguaje me da la posibilidad de crear mis propios tipos de datos...
                                        con sus propiedades y sus comportamientos (métodos)
                                                                propiedades                 Métodos
                                                String          secuencia de caracteres     longitud, a mayúsculas ...
                                                List            secuencia de elementos      add, remove, size, get ...
                                                Calendar        fecha y hora                getDay, getMonth, getYear ... Dime si cae en jueves
                                                                dia, mes, año, hora, minuto, segundo
                                                Diccionario     idioma, palabras, significados   getSignificados, getAlternativas, existe


                                            
## Programación funcional

String texto =  "hola";
// "hola"                   Pone en memoria RAM un objeto de tipo String con el valor "hola"
// String texto             Crea una variable de tipo String (que puede referenciar (apuntar) A UN objeto de tipo String)
// =                        Asigna la variable al texto

texto = "adios";
// "adios"                 Pone en memoria RAM un objeto de tipo String con el valor "adios", en un sitio diferente al anterior
// texto                   La variable texto, la arranco de donde la tenia pegada
// =                       Asigna la variable al nuevo texto

Consumer<String> miFuncion = System.out::println;
miFuncion.accept("Hola amigo");

En Java 1.8 sale el operador ::, que permite referenciar a una función

    System.out::println

---
# SpringBatch: Librería dentro de Spring para procesamiento de datos en batch

Quiero un programa que:         ASI ES COMO TRABAJAMOS CON SPRING
- Lea personas de un fichero CSV (Fecha de nacimiento...)
- Al leer los datos de una persona, que le calcule la edad
- Ah, al iniciar el proceso de carga, que mande un email
- Quiero filtrar a las personas sin email
- Ah, y quiero filtrar a los menos de 18 años
- Ah, y quiero que cuando acabe mande otro email
- Quiero que también filtre a los que tienen un DNI inválido
- Ah, y que cuando acabe de procesar una persona, la guarde en una BBDD
- Ah y quiero que al leer los datos de una persona, si están mal, que los deje anotados en un fichero aparte, para su revisión manual posterior.

Describir el comportamiento que quiero para mi sistema.
Eso es una aplicación? NO... ahí no hay código. Es un comportamiento.

# Según una técnica tradicional: ALGORITMO !

Paso 0: Mandar un email
Paso 1: Comenzar la lectura del fichero csv 
Paso 2: Para cada persona (linea del fichero)                                               FOR
    Paso 2.2: Calcular la edad
    Paso 2.3: Filtrar a los menores de 18 años                                              IF
    Paso 2.4: Filtrar a los que no tienen email
    Paso 2.5: Filtrar a los que tienen un DNI inválido
    Paso 2.6: Si el usuario no tiene datos incorrectos, guardar en BBDD                     IF
    Paso 2.6b: Si el usuario tiene datos incorrectos, guardar en fichero de errores
Paso 3: Mandar un email de despedida!

En Spring, yo no voy a montar la aplicación. La aplicación la monta Spring:
- Pasamos de un lenguaje Imperativo a un lenguaje Declarativo

public class MiSpringApp{

    public static void main(String[] args){
        SpringApplication.run(MiSpringApp.class, args);     // Inversión de control
    }

}

// Spring pregunta... y qué hace tu aplicación?
// Qué módulos/componentes tiene?
// Y mi trabajo es definir esos módulos/componentes
// Para que Spring haga uso de ellos

Para conseguir explicarle a Spring los componentes que debe tener mi aplicación, y las relaciones entre esos componentes, usamos anotaciones.

## Anotaciones

@Autowired          **** Esta ya no se usa! Está obsoleta. No es buena práctica

---

@Component          Que cuando alguien solicite una instancia de la clase con esta anotación... 
                    o una interface que sea implementada por la clase con esta anotación
                    Le sea entregada en automático por Spring... a priori tratando esa instancia como un singleton
@Service
@Repository
@Controller
---
@Configuration
@Bean               Si puedo usar @Component, o sus derivados, siempre será una opción favorita antes que usar la etiqueta @Bean
                    @Bean es una etiqueta que ponemos en funciones.
                    Cuando alguien solite uan instancia de lo que devuelva la función, le será entregada una instancia de lo que devuelva la función (su return)
                    Teniendo en cuenta que (a priori) esa función es invocada una sola vez por Spring... y por ende se me asegura que siempre se devuelve la misma instancia del objeto

# Cómo indicar a Spring qué debe usar para hacer una inyección de dependencias

interface MiImpresor{

    void imprimir(String texto); // Quizás haya implementaciones que impriman en consola... o en una impresora... o en un fichero

}

@Component
class ImpresorConsola implements MiImpresor{

    @Override
    public void imprimir(String texto){
        System.out.println(texto);
    }

}

Al hacer esto, consigo que cuando alguien pida una implementación de MiImpresor, le sea entregada una instancia de ImpresorConsola
Si se solicita en varios sitios o varias veces una instancia de MiImpresor, siempre se entregará la misma instancia de ImpresorConsola
Es decir, se trata ImpresorConsola como sie fuera un singleton

public class MiSingleton {

    private static volatile MiSingleton instancia;

    private MiSingleton(){
        // mi codigo 
    }

    public static MiSigleton dameInstancia(){
        if(instancia == null){ // Rendimiento
            synchronized(MiSingleton.class){ //Semaforo
                if(instancia == null){
                    instancia = new MiSingleton();
                }
            }
        }
        return instancia;
    }

}

@Service, @Repository @Controller, a priori de lo que me informan es de especialización del componente:

- Repositorio: Agrupan conjunto de funciones que se encargan de la persistencia de datos
- Servicio: Agrupan conjunto de funciones que se encargan de la lógica de negocio
- Controlador: Agrupan conjunto de funciones que se encargan de exponer el acceso a los servicios

Esas anotaciones tienen el mismo comportamiento que la anotación @Component... podrán tener comportamientos adicionales.

Servicio Web ... rara vez lo montamos así... de hecho no tiene sentido nunca montarlo así:
    tendré un Servicio...
    Y ese servicio lo expondré, entro otras potenciales formas, mediante acceso Web (http), de hecho incluso varias veces en un proyecto, de formas distintas.

---

@Configuration
public class Listados {
    @Bean
    public List<Persona> dameLasPersonas(){     // Y el nombre que ponga aqui (a la función) es anecdótico
        return new ArrayList<Persona>();
    }
}

A cualquier que pida un List<Persona> se le entrega un ArrayList<Persona>... es más... aunque haya 10 tios pidiendo un List<Persona>... 
siempre se les entregará el mismo ArrayList<Persona>

Para conseguir realmente esto de arriba, cualquier clase que lleve dentro @Bean, al menos 1, debe llevar @Configuration

public class Listados{

    private static final volatile List<Persona> lista = new ArrayList<Persona>();

    public static List<Persona> dameLasPersonas(){
        return lista;
    }

}

---

Todas esas anotaciones me permiten explicar(especificar) a Spring, que cuando se solicite una instancia de un Tipo concreto (clase o interfaz), Spring entregue una instancia de 
la clase que haya anotada con @Component o sus derivados (o lo que devuelva una función anotada como @Bean )

/// A partir de aquí es cuando explicamos COMO PEDIR Instancias Spring

Cómo le indico a Spring que se nos entregue una instancia de un tipo concreto.

# Procedimiento 1: simplemente solicitar el dato a Spring en una  función que sea invocada por Spring

---
public interface EnviadorDeEmails {
    void enviarEmail(String destinatario, String asunto, String cuerpo);
}

---
@Component
public class MiSuperEnviadorDeEmails implements EnviadorDeEmails{
    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpo){
        //...
    }
}

---

import com.curso.EnviadorDeEmails;
//import com.curso.MiSuperEnviadorDeEmails; Ya no hace falta y  dejo de pisotear vilmente el principio de inversión de dependencias

public class procesarFicheroPersonas{

    public void procesar(EnviadorDeEmails enviadorDeEmails){ // Esto funcionaría?
        // Quiero enviar un email
        enviadorDeEmails.enviarEmail("felipe@felipe.com", "Comienzo del procesamiento", "Hola Felipe, te informamos que hemos comenzado el procesamiento del fichero de personas");
        ...
    }
}

// Spring va a suministrar ese valor automáticamente? Depende
// Es Spring quien llama a esa función?
// - si: Spring suministra el valor automáticamente
// - no: Spring no suministra el valor automáticamente. En este caso, necesito una patrón alternativo:

# Procedimiento 2: Anotar con @Autowired la variable que quiero que Spring me suministre

import com.curso.EnviadorDeEmails;
//import com.curso.MiSuperEnviadorDeEmails; Ya no hace falta y  dejo de pisotear vilmente el principio de inversión de dependencias

@Component
public class ProcesarFicheroPersonas{

    @Autowired  // Esto funciona mediante Reflection... que es un procedimiento MUY CARO !
    private EnviadorDeEmails enviadorDeEmails; 

    public void procesar(){ // Esto funcionaría?
        // Quiero enviar un email
        enviadorDeEmails.enviarEmail("felipe@felipe.com", "Comienzo del procesamiento", "Hola Felipe, te informamos que hemos comenzado el procesamiento del fichero de personas");
        ...
    }
}

// @Autowired le indica a Spring, que cuando cree la clase, le asigne un valor a esa variable... que es SU RESPONSABILIDAD no la mia!

import com.curso.ProcesarFicheroPersonas;

public class MiAplicacion{

    public static void otraFuncion(ProcesarFicheroPersonas pfp){ // Esta función tendría que ser invocada por Spring?
        pfp.procesar();
    }

}

// Autowired ya no se recomienda su uso... es de esas cosas que el Sonar os tira a la cara.
// Cual es la buena práctica a día de hoy

import com.curso.EnviadorDeEmails;

@Component
public class ProcesarFicheroPersonas{

    private EnviadorDeEmails enviadorDeEmails;

    public ProcesarFicheroPersonas(EnviadorDeEmails enviadorDeEmails){
        this.enviadorDeEmails = enviadorDeEmails;
    }

    public void procesar(){ // Esto funcionaría?
        // Quiero enviar un email
        enviadorDeEmails.enviarEmail("felipe@felipe.com", "Comienzo del procesamiento", "Hola Felipe, te informamos que hemos comenzado el procesamiento del fichero de personas");
        ...
    }
}

// Solicitar datos en el constructor es la buena práctica a día de hoy
// Esto tiene una ventaja. El valor del enviadorDeEmails lo tengo disponible desde el constructor
// Mientras que con @Autowired, no lo tengo disponible hasta después de que se haya ejecutado el constructor
---
public interface EnviadorDeEmails {
    void enviarEmail(String destinatario, String asunto, String cuerpo);
}

---
@Component
public class MiSuperEnviadorDeEmails implements EnviadorDeEmails{
    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpo){
        //...
    }
}
@Component
public class MiKKEnviadorDeEmails implements EnviadorDeEmails{
    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpo){
        //...
    }
}


---

# Metodologías ágiles

- Scrum
- SAFe
- Kanban
- Extreme Programming

Ir entregando el producto de forma incremental a mi cliente

---

# Metodologías tradicionales: Metodologías en cascada 

-> Toma de requisitos (no hay vuelta atrás) 
    -> Análisis / Diseño del sistema en su conjunto
        -> Escribir código como un bendito
            -> Pruebas
                -> Implantación
                    -> Mantenimiento

# Manifiesto agil:

Entregamos software funcional frecuentemente, entre dos semanas y dos meses, con preferencia al periodo de tiempo más corto posible.

Quiero entregar cada mes.
 Y que me feedback muy rápido 

 Y voy a tener equipos diferentes de trabajo.

---

Sistema para la tienda de animalitos Fermin!

BBDD de animalitos      Programas que operen                                                    Frontal Web (Angular)  [Trabaja con v1.0.0 del alta de animalitos]
                        sobre esa BBDD de animalitos                                            Frontal Móvil (Android)  [v2.0.0]
                                                                                                Front Movil (IOS) [v1.0.0]
                        Busqueda de animalitos <<< API                                          Frontal De escritorio (empleados de la tienda) [v1.1.0]
                                                                                                Frontal Sistema de Voz Interactivo [v1.0.0]
                        Alta de animalitos  <<< API Web                                         Frontal Sistema de Reconocimiento de Voz (Siri, Alexa, Ok Google) [v1.1.0]
                            v1.1.0
                                nombre, tipo, color, edad, peso (opcional)
                            v2:
                                name, type, color, age, pics

v1 o v2? A qué me refiero? MAJOR !

---

Cómo marcamos habitualmente una versión de software:

    1.2.3

                Incrementan?
    Major 1     Breaking changes
                    // Quitar algo que estaba como deprecated
    Minor 2     Añadimos funcionalidad
                Marcamos una funcionalidad como deprecated
                    + arreglos de bugs
    Patch 3     Al arreglar bugs

---

# Microservicios

Alta de animalitos -> API HTTP (ApiRest)

Entidad Animalito               JAVA

Repositorio de animalitos       JAVA
    Funciones para persistir y recuperar Animalitos de algún sitio CRUD

Servicio de animalitos          JAVA
    Funciones de negocio
        - Alta de animalitos
            Implica solo guardar el animalito en el repositorio? Y devolver el ID con el que se ha creado
            Mandar un email a los clientes diciendo que hay un nuevo animalito
            -> KAFKA (nuevo animalito) <-- Lector y enviador de emails (BBDD Subscripciones en mi website)

Controlador del servicio de animalitos   JAVA (HTTP REST) @RestController
    Ese servicio es el API REST? NO ! Es el servicio de animalitos

    Otra cosa es que en un momento dado, quiera exponer ese servicio mediante un API REST
    o mediante un API SOAP
    o mediante un API Remote Procedure Call RPC
    o que se invoque desde java
    o que quiera exponerlo mediante HTTP Rest de 2 formas diferentes (v1, v2)
...

    LOGICA                                                      DATOS
CAPA FRONTAL
    Componentes Angular
CAPA EXPOCICION DEL SERVICIO
    Controlador Rest
        postAnimalito () {
            ServicioDeAnimalitos.altaDeAnimalito( AnimalitosDTO ??? )
        }
CAPA LOGICA NEGOCIO
    Servicio                                                    AnimalitosDTO (pojo)

    class ServiciosDeAnimalitos implementa ServiciosDeAnimalitosInterfaz{
        public DatosAnimalitoDTO altaDeAnimalito(DatosNuevoAnimalitoDTO nuevoAnimalito){
            //...Logica
                                                                DatosModificarUnAnimalitoDTO
                                                                         ^^^^
        }                                                       DatosNuevoAnimalitoDTO
                                                                        ^^^^
                                                                DatosAnimalitoDTO
    }                                                                   v mappers

CAPA ENTIDADES
    RepositorioDeAnimalitos                                     Animalitos (id, nombre, tipo, color, edad)
        Spring-jpa
---
Pruebas! TDD, BDD

Pruebas unitarias, integración, sistema...

Mock    
Stub
Spy
Fake ...