
Entidad             Lo que se guarda en BBDD
    nombre
    apellidos
    edad

ServicioDTO         Lo que maneja a nivel del API Java (con la lógica)
    nombre 
    apellidos
    edad
/*
ControladorDTOv1      Lo que se expone por rest
    nombre < nombre + " " + apellidos
    edad
*/
ControladorDTOv2      Lo que se expone por rest
    nombre
    apellidos
    edad

                    {"nombre": "Ivan", "edad": 44}

Otras APIS o componente WEB

Componente Web con Angular

DatosDeEntrada          v2
    nombre 
    apellidos
    edad

DatosInternos que él maneja     v2
    nombre < nombre + " " + apellidos
    edad

---

v2
    nombre
v2
    nombre 
    apellidos

---
REPO
    maven
    submodules REPO    ENTIDADES
        maven
    submodules REPO    interfaz del servicio
        maven
    submodules REPO    implementación del servicio
                        Sus clases cómo son? publicas o privadas? PUBLICAS Me tem que no. ESO ES UNA FALTA DE SEGURIDAD GRAVE
                        Para eso está el cargador de servicios que es usando iuntermanete por Spring
        maven
    submodules REPO    API REST
        maven
    submodules REPO    Controlador que implementa el API REST
        maven

Hasta java 8 me faltaban modificadores de privacidad

 public
 private
 protected
 friendly
----
    API Diccionario com.curso.diccionario
        interface Diccionario
        interfaz SuministardorDeDiccionarios
    IMPLEMENTACION  
        module - implementacion-diccionario
                com.curso.diccionario
                    import com.curso.diccionario.impl.SuministradorDeDiccionariosDesdeFichero;
                    public interface SuministradorDeDiccionariosFactory
                         static SuministradorDeDiccionarios getInstance(){
                                return new SuministradorDeDiccionariosDesdeFichero;
                        }   
                com.curso.diccionario.impl
                    public class SuministradorDeDiccionariosDesdeFichero
                    public class DiccionarioDesdeFichero

    APP
        class App
                new SuministradorDeDiccionariosDesdeFichero();
                new DiccionarioDesdeFichero();en lugar de usar el método getInstance()-...
                Y si ahí se usa una cache?


        La app solicita una instancia de un SuministradorDeDiccionarios (Spring, JAVA 1.9)
            - A nivel del classpath

module implementacion-diccionario{
    exports com.curso.diccionario;
}
