package com.challenge.ServicioClima;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ServicioClima.entidades.Clima;
import com.challenge.ServicioClima.servicios.ServicioCiudad;
import com.challenge.ServicioClima.servicios.ServicioClima;
import com.challenge.ServicioClima.servicios.ServicioClimatico;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ClimaController {

    @Autowired
    private ServicioClima servicioClima;
    @Autowired
    private ServicioCiudad servicioCiudad;

    @Autowired
    private ServicioClimatico servicioClimatico;
    
    @PostMapping("/obtenerClima")
    public Clima saveClima(@RequestBody String ciudad) throws UnsupportedEncodingException {
        servicioCiudad.obtenerClaveCiudad(ciudad);
        String key = servicioCiudad.obtenerClaveCiudadDesdeAPI();
        servicioClimatico.obtenerClima(key, ciudad);
        return servicioClima.guardarDatosClima(this.servicioClimatico.procesarRespuestaClima());
    }

    @GetMapping("/getClimas")
    public List<Clima> getClimas() {
        return servicioClima.obtenerListadoClima();
    }
}