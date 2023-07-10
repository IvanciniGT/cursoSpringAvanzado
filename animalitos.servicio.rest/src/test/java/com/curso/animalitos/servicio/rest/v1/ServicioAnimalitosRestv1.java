package com.curso.animalitos.servicio.rest.v1;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class ServicioAnimalitosRestv1 {
    @Dado("un objeto JSON,")
    public void unObjetoJSON() {
    }

    @Y("ese objeto json tiene por {string}: {string}")
    public void eseObjetoJsonTienePor(String arg0, String arg1) {
    }

    @Y("ese objeto json tiene por {string}: <edad>")
    public void eseObjetoJsonTienePorEdad(String arg0) {
    }

    @Cuando("hacemos una petición por método {string} al servicio {string}, enviando el objeto JSON,")
    public void hacemosUnaPeticiónPorMétodoAlServicioEnviandoElObjetoJSON(String arg0, String arg1) {
    }

    @Entonces("se recibe una respuesta con código de respuesta {string}")
    public void seRecibeUnaRespuestaConCódigoDeRespuesta(String arg0) {
    }

    @Y("la respuesta debe contener un objeto JSON,")
    public void laRespuestaDebeContenerUnObjetoJSON() {
    }

    @Y("el objeto JSON de respuesta debe tener por {string}: {string}")
    public void elObjetoJSONDeRespuestaDebeTenerPor(String arg0, String arg1) {
    }

    @Y("el objeto JSON de respuesta debe tener por {string}: <edad>")
    public void elObjetoJSONDeRespuestaDebeTenerPorEdad(String arg0) {
    }

    @Y("el objeto JSON de respuesta debe tener por {string} un número entero")
    public void elObjetoJSONDeRespuestaDebeTenerPorUnNúmeroEntero(String arg0) {
    }

    @Y("ese objeto json tiene por {string}: {int}")
    public void eseObjetoJsonTienePor(String arg0, int arg1) {
    }

    @Y("la respuesta debe estar vacía.")
    public void laRespuestaDebeEstarVacía() {
    }
}
