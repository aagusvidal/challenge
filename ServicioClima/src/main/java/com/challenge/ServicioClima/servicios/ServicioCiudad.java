package com.challenge.ServicioClima.servicios;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;
import com.challenge.ServicioClima.helpers.ConvertURLDecoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

@Service
public class ServicioCiudad {
    private String API_KEY;
	private String ciudad;
    private String codigoPais;
	private String URL;

    public ServicioCiudad() {
		API_KEY = "6Clh18qeJNw5NryBiyLS0SmXIYGdoWPN";
        codigoPais="AR";
	}

    public void obtenerClaveCiudad(String ciudad) throws UnsupportedEncodingException {
		this.ciudad = ConvertURLDecoder.codificarURL(ciudad);
		this.URL = "http://dataservice.accuweather.com/locations/v1/cities/"+this.codigoPais+"/search?apikey="+this.API_KEY+"&q="+ this.ciudad +"&language=es&details=false&offset=1&alias=Never";
		
	}

	public String traerDatos() {
        try {
            StringBuilder resultado = new StringBuilder();
            URL url = new URL(this.URL);
            URLConnection conexion = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

            String linea;
            while ((linea = rd.readLine()) != null)
                resultado.append(linea);

            rd.close();
            return resultado.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String obtenerClaveCiudadDesdeAPI() {
        try {
            String datosJSON = traerDatos();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(datosJSON);

            if (jsonNode.isArray() && jsonNode.size() > 0) {
                JsonNode primerElemento = jsonNode.get(0);
                String key = primerElemento.path("Key").asText();
                return key;
            } else {
                return "No se encontraron resultados";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}