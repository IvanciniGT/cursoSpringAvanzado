package com.curso.emails.servicio.impl;

import com.curso.emails.servicio.EmailsServicio;
import org.springframework.stereotype.Service;

@Service
public class EmailsServicioImpl implements EmailsServicio {
    @Override
    public void enviarEmail(String destinatario, String asunto, String cuerpo) {
        System.out.println("Enviando email a " + destinatario + " con asunto " + asunto + " y cuerpo " + cuerpo);
    }

    @Override
    public String getDestinatarioPorDefecto() {
        return null;
    }
}
