package com.curso.animalitos.servicio.testdoubles;

import com.curso.emails.servicio.EmailsServicio;
import lombok.Getter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
//@Primary
@Getter
// Con esta anotación le digo a Spring... Oye, Spring... si hay por ahí otra implemntación del servicio de emial.. pasa de ella.
// Yo soy la que tienes que usar.
public class EmailsServicioSpy implements EmailsServicio {

    private int numeroDeLlamadas = 0;
    private String asunto;
    private String cuerpo;
    private String destinatario;

    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpo) {
        numeroDeLlamadas++;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.destinatario = destinatario;
    }

    @Override
    public String getDestinatarioPorDefecto() {
        return null;
    }

}
