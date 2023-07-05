
# Cómo solicitar a Spring una inyección de dependencias

## Metodo A - Deprecated - Mediante el uso de la anotación @Autowired

```java
    @??? Alguna anotación que haga que Spring genere la instancia de la clase ***
    public class MiClase {

        @Autowired
        private Dato miDato;

    }
```

> Quiero una instancia de un objeto de tipo Dato, que me la enchufe en la propiedad miDato.
> Cuando Spring hace esto? 
>   - Después de generar la instancia de la clase (de llamar a su constructor)
>           OJO !!! Estamos dando por sentado que ES SPRING EL QUE VA A INSTANCIAR LA CLASE!
>                   Y, o se lo pido expresamente, o no es algo que Spring vaya a hacer por que si. ***
>           Durante el constructor, miDato no está disponible... esto tendrá mayor o menor relevancia según el caso.

> *** Cómo le puedo indicar a Spring que sea él quien genere una instancia de una clase? 
>       Con una anotación que le indique, entre otras cosas, que debe ser él quien genere la instancia:
>           @Component (y sus derivados: @Repository, @Service, @Controller), @Configuration

## Metodo B - Guay - Solicitando el dato como argumento de una función que sea invocada por Spring

```java
    public class MiClase {

        public LO_QUE_SEA miFuncionQueEsLlamadaPorSpring( Dato miDato ){
            // Mi código 
        }

    }
```

> OJO: Spring es el que tiene que llamar a la función... Si no... mal asunto, no voy a tener el dato... o me toca a mi suministrarlo.
>       Tenemos 2 tipo de funciones (formas de indicar a Spring ) para que se entere que es él quien debe llamar a una función:
>           - Marcar la función como @Bean, dentro de una clase marcada como @Configuration, Spring es quien automáticamente llamará a la función... 
>                  inyectando lo que tenga que inyectar
>               OJO: ESTO TIENE EFECTOS COLATERALES!

```java
    @Configuration
    public class MiClase {

        @Bean
        public LO_QUE_SEA miFuncionQueEsLlamadaPorSpring( Dato miDato ){ // Aqui Spring hace la inyección 
            // Mi código 
        }

    }
```

>           - Spring llamará al constructor de cualquier clase que él deba crear.

```java
    @??? Alguna anotación que haga que Spring genere la instancia de la clase ***
    public class MiClase {

        private final Dato miDato;

        public MiClase( Dato miDato ){
            this.miDato = miDato;
        }

        public LO_QUE_SEA miFuncionQueNoEsLlamadaPorSpring( ){
            // Mi código que puede usar miDato, ya que se ha rellenado al crearse la clase
        }

    }
```

---

# Cómo indicar a Spring qué debe entregar cuando se solicite una inyección de dependencias

## Si tiene que entregar una instancia de una clase MIA (que yo defino)

Entonces marco la clase como: @Component o uno de sus derivados

```java
    @Component // O uno de sus derivados
    // ^^^ Esto le indica a Spring que cuando alguien solicite una inyección de dependencias de un objeto de este tipo, 
    // Spring debe generar una instancia (new)... y entregarla
    // Esa instancia, una vez generada, se entregará a TODOS las clases y métodos que soliciten un dato de ese tipo. LA MISMA INSTANCIA: (Comportamiento tipo SINGLETON)
    // Dato dato1 = new Dato();
    public class Dato {
        // Mis métodos
    }

    @??? Alguna anotación que haga que Spring genere la instancia de la clase ***
    public class MiClase {

        private final Dato miDato;

        public MiClase( Dato miDato ){ // Se le entrega la instancia dato1
            this.miDato = miDato;
        }

        public LO_QUE_SEA miFuncionQueNoEsLlamadaPorSpring( ){
            // Mi código que puede usar miDato, ya que se ha rellenado al crearse la clase
        }

    }

    @??? Alguna anotación que haga que Spring genere la instancia de la clase ***
    public class MiClase2 {

        private final Dato miDato;

        public MiClase2( Dato miDato ){ // Se le entrega la instancia dato1... no se entrega otra instancia de la clase Dato... sino la misma que se haya entregado ya antes.
            this.miDato = miDato;
        }

        public LO_QUE_SEA miFuncionQueNoEsLlamadaPorSpring( ){
            // Mi código que puede usar miDato, ya que se ha rellenado al crearse la clase
        }

    }
```

> En ese escenario, al llamar al constructor de MiClase, Spring suministrará una instancia de la clase Dato
>      NOTA: Esto funcionaría igual si tengo una clase que implementa una interfaz del tipo solicitado:

```java
    public interface Dato {
        // Mis métodos
    }

    @Component // O uno de sus derivados
    public class Dato2Impl implements Dato {
        // Mis métodos
    }

    @??? Alguna anotación que haga que Spring genere la instancia de la clase ***
    public class MiClase {

        private final Dato miDato;

        public MiClase( Dato miDato ){
            this.miDato = miDato;
        }

        public LO_QUE_SEA miFuncionQueNoEsLlamadaPorSpring( ){
            // Mi código que puede usar miDato, ya que se ha rellenado al crearse la clase
        }

    }
```

> En ese escenario, al llamar al constructor de MiClase, Spring suministrará una instancia de la clase DatoImpl
> > NOTA 2: De hecho con cuál de las 2 opciones queremos trabajar? 
> > Porque si no... ya he vinculado MiClase a la Implementación de una Interfaz = CAGARME EN EL PPO DE INVERSION DE DEPENDENCIAS !!!! 
>  Y ESO NO QUIERO HACERLO !!

## Si tiene que entregar una instancia de una clase que no es MIA (que yo no defino)

Si la opción 1 me sirve... dejamos de leer lo que viene a continuación.
Si no me sirve... necesito una alternativa.
No me servirá si la clase que quier que Spring instancie, para entregar cuando alguien solicite la inyección de dependencias, no es mia...
Y no tengo opción de anotar esa clase.

En este caso, lo que hacemos es definir una función que devuelva el TIPO DE DATO que alguien puede solicitar mediante inyección de dependencias.
Esa función la anotaré como @Bean... dentro de una clase anotada como  @Configuration

```java
    public interface Dato {
        // Mis métodos
    }
    
    public class DatoImpl implements Dato {
        // Mis métodos
    }
    ///////////////////////// ^^^ ESTAS CLASES / INTERFACES DE AHI ARRIBA NO SON MIAS ... Y NO VIENEN CON ANOTACIONES RARAS 

    @Configuration
    public class ConfiguracionDeMiApp {

        @Bean
        // ^ OYE SPRING, cuando alguien te pida un dato de tipo "Dato", le entregas lo que devuelve esta función.
        public Dato miFuncionQueEspecificaASpringLoQueDebeDevolverCuandoAlguienPidaUnDATO() {
            // Yo creo la instancia... No Spring
            // Puede ser que aquí necesite datos adicionales para generar esta instancia
            return new DatoImpl();
        }
        // NOTA: Spring solo va a llamar a esta función 1 vez (COMPORTAMIENTO SINGLETON DE NUEVO) ... y cachea la instancia que se ha devuelto... Y esa es la que entrega siempre!

    }
    // Esa función se ejecuta al arrancar la aplicación. Spring cachea el resultado... y cuando alguien solicite en "Dato" a partir de ese momento,
    // Esa instancia cacheada es lo que se devuelve.

    @??? Alguna anotación que haga que Spring genere la instancia de la clase ***
    public class MiClase {
        
        @Value{SINTAXIS QUE LE PERMITIRA A SPRING BUSCAR ESTE DATO EN EL FICHERO DE CONF DE MI APP}
        private String unTextoQueMeHaceFalta;
        
        private final Dato miDato;

        public MiClase( Dato miDato ){
            this.miDato = miDato;
        }

        public LO_QUE_SEA miFuncionQueNoEsLlamadaPorSpring( ){
            // Mi código que puede usar miDato, ya que se ha rellenado al crearse la clase
            // Puede ser que aquí necesite datos adicionales para procesar esta petición, como por ejemplo unTextoQueMeHaceFalta
        }

    }
```