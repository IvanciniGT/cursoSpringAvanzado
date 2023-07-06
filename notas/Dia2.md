# Pruebas de software

## Vocabulario en el mundo de las pruebas de software

- Error:    Los humanos cometemos errores... (por falta de conocimiento, estar despistados, cansados...)
- Defecto:  Al cometerlos introducimos DEFECTOS en un producto
- Fallo:    Esos defectos pueden manifestarse en forma de FALLOS

## Para que sirven las pruebas

- Para asegurar el cumplimiento de un requisito
- Para tratar de identificar el mayor número posible de Fallos antes del paso a producción
    Una vez identificado un fallo, será necesario identificar el DEFECTO que provoca el fallo, para su subsanación
    Ese procedimiento, no es parte del testing... y se llama: DEPURACION o DEBUGGING
    - Una de las cosas para las que sirven las pruebas, es para RECOPILAR INFORMACION que facilite el DEBUGGING (1)
- Para tratar de identificar el mayor número posible de Defectos antes del paso a producción.
- Para tratar de identificar los ERRORES Que están dando lugar a los defectos (mediante un análisis de causas raices)
  Y proponer en consecuencia acciones preventivas, que eviten nuevos ERRORES (y por ende, defectos y fallos ) en el futuro.
- Para saber qué tal va el proyecto !!! <<<< CRITICO EN MET. AGILES
- Para ayudarnos en el desarrollo del proyecto: METODOLOGIAS DE DESARROLLO DE SOFTWARE: TDD, BDD, ATDD

DEL MANIFIESTO AGIL:

Entregamos software FUNCIONAL frecuentemente, entre dos semanas y dos meses, con preferencia al periodo de tiempo más corto posible.

    Qué significa FUNCIONAL? que funciona
    Quién dice que el software funciona? Pruebas 

La medida principal de progreso que vamos a usar en un proyecto es El "software funcionando"

    Quién me dice que el software funciona? LAS PRUEBAS 

    Aquí se nos habla de qué indicador vamos a usar para saber cómo va el proyecto. Vamos bien??

    HITO 1: 15 de Julio : *R1, R2, R3, R4*

        Qué pasaba si llegado el día 15 de Julio no tenía el R1, R2, R3 y R4? Le preguntaba al desarrollador.
        - Alarmas
        - Ostias pa tos los laos
        - Vamos con retraso: REPLANIFICABA EL HITO -> 23 de Julio

    SPRINT: *15 de Julio* : R1, R2, R3, R4
        Qué pasaba si llegado el día 15 de Julio no tenía el R1, R2, R3 y R4? Solo tengo el R1 y el R2?
            Se monta en producción el R1 y el R2... pero la fecha no se toca ! Y el r3 y el R4 se pasan al siguiente

--- 
Sprint  1      Dia 30 del proyecto: 5% de la funcionalidad en producción
            Pruebas a nivel de producción       5%
Sprint 2       Dia 50 del proyecto: +10% de la funcionalidad en producción
            Pruebas a nivel de producción       10% + 5%
Sprint 3       Dia 50 del proyecto: +10% de la funcionalidad en producción
            Pruebas a nivel de producción       10% + 10% + 5%

La met ágiles, han venido con sus nuevos problemas... y eso no me lo cuentan lo scrumcitos... and company (corbatillas)
- Las pruebas se multiplican como enanitos!!!!
- Las instalaciones se me multiplican!
Y de donde sale la pasta ???? y el tiempo ??? y los recursos??? NO LA HAY !!!!
Es inasumible en un proyecto.. al menos hacerlo como lo hacíamos antes: AUTOMATIZAR !

## Tipos de pruebas

Las pruebas las clasificamos en base a distintas taxonomias (que van paralelas entre si)

### En base al nivel de la prueba:

Cualquier prueba debe centrarse en un UNICO ASPECTO del sistema/componente > (1)

- Unitarias             En una característica de un componente AISLADO del sistema

                            TREN: Motor, Ruedas, Sistema de frenos, sistema de transmisión(bielas)

                        Para poder hacer una pruebas unitaria, que es lo primero que necesito? AISLAR EL COMPONENTE

                            public Informe generarInforme(Datos datosDelInforme){
                                // Conecto a la BBDD para sacar más datos
                                // LLamo a una librería que hace cálculos
                                // Envió todo a otra librería que genera un PDF
                            }

                        En los casos en los que un componente depende de otros (y son muchos casos... lo habitual), vamos a necesitar tirar de:
                            TEST DOUBLES: (mocks para referirnos a todos ellos, como sinónimo de test deouble)
                            - Stubs
                            - Mocks
                            - Fakes
                            - Dummies
                            - Spies

- De Integración        En la COMUNICACION entre componentes

                            Sistema de frenos  > comunicación > Ruedas 

- End2End               Se centran en el COMPORTAMIENTO

                            Con todo montado, el tren va pa'trás. CAGADA !!!

    Si hago todas las pruebas de Sistema (End2End) y todo funciona adecuadamente, necesito hacer pruebas unitarias y de integración? NO
    - 1º Y si no funciona? dónde está el problema? 
    - 2º Cuándo puedo hacer estas pruebas? El sistema en su conjunto... y hasta entonces no hago pruebas? 

  - De Aceptación     Suelen ser un subconjunto de las anteriores

### En base al objeto de prueba:

Pruebas funcionales:            Se centran en la funcionalidad
Pruebas no funcionales:
    - Rendimiento
    - Carga
    - Estres
    - UX
    - HA
    - ...

### En base al procedimiento de ejecución de la prueba

- Pruebas Estáticas:
  - Revisión de requisitos
        REQUISITO 1: El sistema debe realizar tal operación con unos tiempos de respuesta adecuados . MIERDA  = AMBIGUO
        REQUISITO 1v2: El sistema debe realizar tal operación tardando menos de 200ms . MIERDA  = AMBIGUO
        REQUISITO 1v3: El sistema, montado en una infraestructura X, y con una configuración Y, cuando está sometido a una carga de trabajo Z, debe responder a la secuencia de operaciones A, con un percentil 95% inferior a 200ms.
  - Revisión de un modelo de datos
  - Revisión del código: SONARQUBE (calidad)
- Pruebas dinámicas: Son las que requieren poner el sistema en funcionamiento

### En base al conocimiento que tengo del objeto de prueba

- Pruebas de caja blanca: Conocemos cómo se ha desarrollado el componente (sus tripas)
- Pruebas de caja negra : NO Conocemos cómo se ha desarrollado el componente (sus tripas)

# Pruebas de regresión

Básicamente, cualquier prueba que repito como consecuencia de haber introducido un cambio en el sistema.

---

## Principios FIRST de desarrollo de pruebas

F ast               Voy a acabar con 500 pruebas o 1000... más vale que se ejecuten rapidito
                    Que si por ejemplo, tengo 400 pruebas que necesiten hacer login previamente,
                        voy a crear un Stub que le cuele cualquier dato al hacer login... y no lo valide realmente... que lleva tiempo
I ndependent        Una prueba no debe depender de otras pruebas (para eso le pongo un punto de partida.)
R epeteable         Una prueba se debe poder repetir las veces que haga falta
S elf-validating    Y debe comprobar todo lo que es necesario
T imely             Y debe estar a tiempo (ser oportuna). De nada me vale tener la prueba el último día.
                        Debo buscar herramientas y técnicas que me permitan montar las pruebas fácilmente

---
# Qué es JUnit?

Es un framework de pruebas automatizadas en JAVA (unitarias, de integración, de sistema)

## Mockito

Es un framework que uso en algunas pruebas, no en todas, para aislar componentes, de forma que pueda hacer PRUEBAS UNITARIAS.

---

# Estamos empezando a aplicar una metodología TDD: TEST DRIVEN DEVELOPMENT

Existe otra metodología llamada Test-First Development: Que me dice... define primero las pruebas y después ponte a escribir código hasta que las pruebas se pongan en verde. Una vez conseguido esto... has acabado. No escribas ni una sola linea más de código:
        La simplicidad, o el arte de maximizar la cantidad de trabajo no realizado, es esencial
        Antiguamente el "voy a dejar esto avanzado por si acaso..." Era considerado una buena práctica.

TDD = Test-First-Development + Refactorización en cada paso.

## Refactorización

Técnica de ingeniería de software para modificar el código y conseguir mejorar su legibilidad, reutilización y mnto, sin modificar su comportamiento.

## BDD = Behavior driven development

Es una extensión de TDD, donde comenzamos definiendo PRUEBAS DE SISTEMA (comportamiento)

# Beneficios de usar Test-First-Development

- Valido el API, antes de ponerme a desarrollarlo.
  No tiene ningún sentido comenzar a implementar un API, que no se si me sirve.
- Entender el comportamiento due debe tener el componente antes de escribir código

public class Animalito {
    private String nombre;
    private String color;
    private String tipo;
    private Integer edad;

    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nuevoNombre){
        this.nombre = nuevoNombre;
    }
}

---
# MAVEN

Es una herramienta para automatizar tareas básicas y repetitivas de mis proyectos JAVA(principalmente):
- compilar
- empaquetar
- ejecutar las pruebas
- mandarlo a sonarqube
- gestionar las dependencias

---
module 
    package
        class
        interface


            x
Servicio -----> Repositorio
  √                 √

√ Pruebas unitarias al Repositorio
    Estas pruebas me garantizan que el repo funciona de forma aislada... Él hace lo que tiene que hacer

x Pruebas unitarias al Servicio
    Aún no las hemos hecho... pero en cualquier caso, lo que garantizarán es que:
    El servicio hace lo que debe.

√ Pruebas de integración que prueban la comunicación entre el servicio y el repo.


