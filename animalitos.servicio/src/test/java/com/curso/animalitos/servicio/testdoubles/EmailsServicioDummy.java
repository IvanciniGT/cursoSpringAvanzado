package com.curso.animalitos.servicio.testdoubles;

import com.curso.emails.servicio.EmailsServicio;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Primary
@Profile("unit-testing-sin-servicio-emails")

public class EmailsServicioDummy implements EmailsServicio {
    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpo) {

    }

    @Override
    public String getDestinatarioPorDefecto() {
        return null;
    }
}
