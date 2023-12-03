package com.challenge.ServicioClima.servicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.stereotype.Service;

import com.challenge.ServicioClima.entidades.Clima;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ServicioClimatico {

    private String API_KEY;
	private String ciudad;
    private String nombreCiudad;
	private String URL;

    public ServicioClimatico() {
		API_KEY = "6Clh18qeJNw5NryBiyLS0SmXIYGdoWPN";
	}

    public void obtenerClima(String ciudad, String nombreCiudad) throws UnsupportedEncodingException {
		this.ciudad = ciudad;
        this.nombreCiudad = nombreCiudad;
		this.URL = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/"+this.ciudad+"?apikey="+this.API_KEY+"&language=es&details=false&metric=false";	
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

    public Clima procesarRespuestaClima() {
        try {
            String datosJSON = traerDatos();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(datosJSON);

            JsonNode headlineNode = jsonNode.path("Headline");
            String date = headlineNode.path("EffectiveDate").asText();
            String descripcionGeneral = this.corregirCodificacion(headlineNode.path("Text").asText());

            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(date, formatter);
            Date fecha = Date.from(offsetDateTime.toInstant());
            JsonNode dailyForecastsNode = jsonNode.path("DailyForecasts");

            if (dailyForecastsNode.isArray() && dailyForecastsNode.size() > 0) {
                JsonNode dailyForecast = dailyForecastsNode.get(0);

                String forecastDate = dailyForecast.path("Date").asText();
                JsonNode temperatureNode = dailyForecast.path("Temperature");
                String unidadTemperatura = temperatureNode.path("Minimum").path("Unit").asText();
                String temperaturaMinima = String.valueOf(temperatureNode.path("Minimum").path("Value").asInt()) + unidadTemperatura;
                String temperaturaMaxima = String.valueOf(temperatureNode.path("Maximum").path("Value").asInt()) + unidadTemperatura;
                
                JsonNode dayNode = dailyForecast.path("Day");
                String descripcionDia = this.corregirCodificacion(dayNode.path("IconPhrase").asText());

                JsonNode nightNode = dailyForecast.path("Night");
                String descripcionNoche = this.corregirCodificacion(nightNode.path("IconPhrase").asText());
                Clima clima =  new Clima(nombreCiudad, fecha, descripcionGeneral, descripcionDia, descripcionNoche, temperaturaMaxima, temperaturaMinima);
                return clima;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String corregirCodificacion(String texto) {
        byte[] bytes = texto.getBytes(StandardCharsets.ISO_8859_1); 
        return new String(bytes, StandardCharsets.UTF_8);
    }
}

