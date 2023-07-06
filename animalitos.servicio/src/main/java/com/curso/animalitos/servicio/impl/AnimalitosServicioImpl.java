package com.curso.animalitos.servicio.impl;

import com.curso.animalitos.entidades.Animalito;
import com.curso.animalitos.entidades.RepositorioAnimalitos;
import com.curso.animalitos.servicio.mappers.AnimalitosDTOMapper;
import com.curso.animalitos.servicio.mappers.AnimalitosMapper;
import com.curso.animalitos.servicio.AnimalitosServicio;
import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;
import com.curso.emails.servicio.EmailsServicio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// Esto hace que si alguien pide un AnimalitosServicio,
// Spring le entregue una instancia de esta clase..
// NOTA: Esta clase va a tener comportamiento de Singleton.
//       Es decir, a todas las personas del mundo que pida una instancia de una AnimalitosServise,
//        se le entrega la misma instancia
public class AnimalitosServicioImpl implements AnimalitosServicio {

    private final EmailsServicio emailsServicio;
    private final RepositorioAnimalitos repositorioAnimalitos;
    private final AnimalitosDTOMapper mapeador;

    public AnimalitosServicioImpl(AnimalitosDTOMapper mapeador, RepositorioAnimalitos repositorioAnimalitos,EmailsServicio emailsServicio) {
        this.emailsServicio = emailsServicio;
        this.repositorioAnimalitos=repositorioAnimalitos;
        this.mapeador = mapeador;
    }

    @Override
    public DatosAnimalitoDTO altaAnimalito( DatosNuevoAnimalitoDTO datosNuevoAnimalitoDTO) {
        Animalito nuevoAnimalito = mapeador.datosNuevoAnimalito2Animalito(datosNuevoAnimalitoDTO);

        // Llamar al repo para que se guarde el dato (animalito)
        Animalito nuevoAnimalitoPersistido = repositorioAnimalitos.save(nuevoAnimalito);

        // Voy a obtener de vuelta los datos del repo (id)
        // Llamar al que envía los emails
        emailsServicio.enviarEmail("subscriptores@animalitos.fermin.com","Nuevo animalito",
                "Amigo, tenemos un nuevo animalito en la tienda: "+ datosNuevoAnimalitoDTO.getNombre()+". Es muy mono. vente a por él");

        // Devolveré el resultado (DatosAnimalitoDTO)
        DatosAnimalitoDTO datosAnimalitoDTO = AnimalitosMapper.animalito2DatosAnimalito(nuevoAnimalitoPersistido);
        return datosAnimalitoDTO;
    }


    @Override
    public Optional<DatosAnimalitoDTO> recuperarAnimalito(Long id) {
        return null;
    }
}
