package com.curso.animalitos.servicio.rest.v1;

import io.cucumber.java.en.Given;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.cucumber.spring.CucumberContextConfiguration;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Junit, aparte de darnos esa librería que define pruebas y aserciones...
// también tiene otra librería que aparece nueva con junit5: junit Platform engine
// Esa librería me permite solicitar a JUnit que delegue la ejecución de algunas pruebas a otros motores de procesamiento de pruebas
// Como por ejemplo Cucumber
@Suite   // Esta clase contiene una suite de pruebas de JUnit
@IncludeEngines("cucumber") // Y en esa suite, hay pruebas que debes ejecutar con el motor: Cucumber
@SelectClasspathResource("features") // Esta es otra anotación de Junit Platform suite... con la que le decimos a Junit que pase el fichero feature... de HEcho un monton de ellos a Cucumber
                         // Lo que hay dentro de la carpeta features
// Esta linea arranca un tomcat, con la app dentro desplegada, para que la use en pruebas.
@SpringBootTest(classes = AplicacionParaPruebas.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Crea y configura un cliente HTTP para atacar al servidor de pruebas
@AutoConfigureMockMvc
// Hace que cucumber pueda usar inyecciónes de Spring
@CucumberContextConfiguration
public class ServicioAnimalitosRestv1 {

    // Aqui voy a tener una referencia a un cliente http... cúal? Al que se ha definido arriba... que se me inyectará
    private final MockMvc clienteHttp;

    public ServicioAnimalitosRestv1(MockMvc clienteHttp) {
        this.clienteHttp = clienteHttp;
    }

    private JSONObject objetoJson;
    private ResultActions respuesta;

    @Given("un objeto JSON,")
    public void unObjetoJSON() {
        objetoJson = new JSONObject();
    }

    @Y("ese objeto json tiene por {string}: {string}")
    public void eseObjetoJsonTienePor(String campo, String valor) throws JSONException {
        objetoJson.put(campo, valor);
    }

    @Y("ese objeto json tiene por {string}: {int}")
    public void eseObjetoJsonTienePorEdad(String campo, int valor) throws JSONException {
        objetoJson.put(campo, valor);
    }

    // A esta función la llamará Cucumber. Si no tengo Cucumber.. pues monto la prueba con Junit
    @Cuando("hacemos una petición por método {string} al servicio {string}, enviando el objeto JSON,")
    public void hacemosUnaPeticiónPorMétodoAlServicioEnviandoElObjetoJSON(String metodo, String endpoint) throws Exception {
        // Servidor de aplicaciones, con una aplicación montada, donde tengamos ese servicio REST corriendo?
        // Tomcat
        // Dentro del tomcat, desplegar un WAR
        // Ese war debe tener dentro: El código del controlador + código de la capa del servicio + código de la capa de entidades(repo) + Servicio de email
        // BBDD
        // Conseguido gracias a la anotación @SpringBootTest

        // Una vez lo tenga, haré la petición por http
        // Tengo un cliente gracias a la anotación @AutoConfigureMockMvc... que se me inyecta cuando pido un MockMvc
        switch(metodo){
            case "post":
                // Y capturaré la respuesta
                respuesta = clienteHttp.perform(
                        MockMvcRequestBuilders.post(endpoint)
                                .contentType("application/json")
                                .content(objetoJson.toString())
                                );
                break;
            case "get" :
                respuesta = clienteHttp.perform(
                        MockMvcRequestBuilders.get(endpoint)
                );
                break;
            case "put":
                // Y capturaré la respuesta
                respuesta = clienteHttp.perform(
                        MockMvcRequestBuilders.put(endpoint)
                                .contentType("application/json")
                                .content(objetoJson.toString())
                );
                break;
        }
    }

    @Entonces("se recibe una respuesta con código de respuesta {string}")
    public void seRecibeUnaRespuestaConCódigoDeRespuesta(String codigo) throws Exception {
        switch(codigo){
            case "CREATED": // Esto es el 201
                respuesta.andExpect(status().isCreated());
                // andExpect, son de SpringTest... y son equivalentes a los assert de Junit
                break;
            case "BAD REQUEST":
                respuesta.andExpect(status().isBadRequest());
                break;
        }
    }

    @Y("la respuesta debe contener un objeto JSON,")
    public void laRespuestaDebeContenerUnObjetoJSON() throws Exception {
        respuesta.andExpect(content().contentType("application/json"));
    }
    @Y("el objeto JSON de respuesta debe tener por {string}: {string}")
    // JsonPath  ...   es un eqquivalente en el mundo JSON al XPATH que existe en el mundo XML
    public void elObjetoJSONDeRespuestaDebeTenerPor(String campo, String valor) throws Exception {
        respuesta.andExpect(jsonPath("$."+campo).value(valor));
    }

    @Y("el objeto JSON de respuesta debe tener por {string}: {int}")
    public void elObjetoJSONDeRespuestaDebeTenerPorEdad(String campo, int valor) throws Exception {
        respuesta.andExpect(jsonPath("$."+campo).value(valor));
    }

    @Y("el objeto JSON de respuesta debe tener por {string} un número entero")
    public void elObjetoJSONDeRespuestaDebeTenerPorUnNúmeroEntero(String campo) throws Exception {
        respuesta.andExpect(jsonPath("$."+campo).isNumber());
    }

    @Y("la respuesta debe estar vacía.")
    public void laRespuestaDebeEstarVacía() throws Exception {
        respuesta.andExpect(content().string(""));
    }
}
