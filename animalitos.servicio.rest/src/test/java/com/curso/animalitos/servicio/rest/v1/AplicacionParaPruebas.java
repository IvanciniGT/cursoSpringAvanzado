package com.curso.animalitos.servicio.rest.v1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// Esta anotación de indica a Spring, que busque entidades, componentes, configuraciones.. de
// to do   en el mismo paquete en el que estoy y sus subpaquetes
// De lo que haya en tiempo de ejecuci`ón en el classpath
// Esta anotación, que nos da SpringBoot, es una anotación que reemplaza a 3 anotaciones que usábamos antes:
@ComponentScan("com.curso")
@EntityScan(basePackages = {"com.curso.animalitos"})
@EnableJpaRepositories(basePackages = {"com.curso.animalitos"})
public class AplicacionParaPruebas {
}
