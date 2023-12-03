package com.challenge.ServicioClima.servicios;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challenge.ServicioClima.entidades.Clima;
import lombok.RequiredArgsConstructor;
import com.challenge.ServicioClima.repositorios.ClimaRepository;

@Service
@RequiredArgsConstructor
public class ServicioClima {

    @Autowired
    private ClimaRepository climaRepository;

    public Clima guardarDatosClima (Clima clima) {
        return climaRepository.save(clima);
    }
    
    public Clima obtenerClima(Integer id){
        Optional<Clima> optionalClima = climaRepository.findById(id);
        return optionalClima.get();
    }

     public List<Clima> obtenerListadoClima(){
        return climaRepository.findAll();
    }

}