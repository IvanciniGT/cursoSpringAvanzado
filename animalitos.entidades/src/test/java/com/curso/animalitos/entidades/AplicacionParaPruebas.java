package com.curso.animalitos.entidades;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Esta anotación de indica a Spring, que busque entidades, componentes, configuraciones.. de
// to do   en el mismo paquete en el que estoy y sus subpaquetes
// De lo que haya en tiempo de ejecuci`ón en el classpath
// Esta anotación, que nos da SpringBoot, es una anotación que reemplaza a 3 anotaciones que usábamos antes:
// @ComponentScan("paquete")
// @EntityScan(basePackages = {"paquete1", "paquete2"})
// @EnableJpaRepositories(basePackages = {"paquete1", "paquete2"})
public class AplicacionParaPruebas {
}
