package com.curso.animalitos.servicio.impl;

import com.curso.animalitos.servicio.AnimalitosServicio;
import com.curso.animalitos.servicio.dtos.DatosAnimalitoDTO;
import com.curso.animalitos.servicio.dtos.DatosNuevoAnimalitoDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// Esto hace que si alguien pide un AnimalitosServicio,
// Spring le entregue una instancia de esta clase..
// NOTA: Esta clase va a tener comportamiento de Singleton.
//       Es decir, a todas las personas del mundo que pida una instancia de una AnimalitosServise,
//        se le entrega la misma instancia
public class AnimalitosServicioImpl implements AnimalitosServicio {
    @Override
    public DatosAnimalitoDTO altaAnimalito(DatosNuevoAnimalitoDTO datosNuevoAnimalitoDTO) {
        return null;
    }

    @Override
    public Optional<DatosAnimalitoDTO> recuperarAnimalito(Long id) {
        return null;
    }
}
