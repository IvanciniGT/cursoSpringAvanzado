package com.curso.emails.servicio;

public interface EmailsServicio {
    void enviarEmail(String destinatario, String asunto, String cuerpo);
    // Spy o Mock
    String getDestinatarioPorDefecto();
}
