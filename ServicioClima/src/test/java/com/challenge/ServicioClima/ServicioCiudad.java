package com.challenge.ServicioClima;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.challenge.ServicioClima.entidades.Clima;
import com.challenge.ServicioClima.repositorios.ClimaRepository;
import com.challenge.ServicioClima.servicios.ServicioClima;
import com.challenge.ServicioClima.servicios.ServicioClimatico;

public class ServicioCiudad {

    @Mock
    private ClimaRepository climaRepository;

    @Mock
    private ServicioClima servicioClima;

    @Mock
    private ServicioClimatico servicioClimatico;

    @Test
    public void testProcesarRespuestaClima() throws UnsupportedEncodingException {
     
        Clima mockClima = mockearClima();
        when(climaRepository.save(any(Clima.class))).thenReturn(mockClima);
        servicioClimatico.obtenerClima("7894", "Buenos Aires");
        Clima resultado = servicioClimatico.procesarRespuestaClima();
        System.out.println("el resultado es"+ resultado);
        verify(climaRepository, times(1)).save(any(Clima.class));
        assertEquals(mockClima, resultado);
    }

    private Clima mockearClima() {
        Clima clima = new Clima( "Buenos Aires",new Date(),"Soleado", "", "Fresco con sol", "50F", "10F");
        return clima;
    }

}
