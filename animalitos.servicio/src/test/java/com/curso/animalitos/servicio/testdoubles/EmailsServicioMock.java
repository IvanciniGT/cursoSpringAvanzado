package com.curso.animalitos.servicio.testdoubles;

import com.curso.emails.servicio.EmailsServicio;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Primary
@Profile("unit-testing-con-servicio-emails")
public class EmailsServicioMock implements EmailsServicio {
    // Un mock es igual que un spy... pero lleva lógica que le permite determinar si se está usando de forma adecuada.

    private String destinatarioTest;
    private String asuntoTest;
    private String contenidoEnElcuerpoTest;

    private boolean invocada;

    public void configure(String destinatario, String asunto, String contenidoEnElcuerpo){
        this.destinatarioTest = destinatario;
        this.asuntoTest = asunto;
        this.contenidoEnElcuerpoTest = contenidoEnElcuerpo;
        this.invocada = false;
    }

    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpo) {
        Assertions.assertEquals(destinatarioTest, destinatario);
        Assertions.assertEquals(asuntoTest, asunto);
        Assertions.assertTrue(cuerpo.contains(contenidoEnElcuerpoTest));
        this.invocada = true;
    }

    @Override
    public String getDestinatarioPorDefecto() {
        // Stubbeando una función.
        return "subscriptores@animalitos.fermin.com";
    }

    public void validate(){
        Assertions.assertTrue(invocada);
    }
}
