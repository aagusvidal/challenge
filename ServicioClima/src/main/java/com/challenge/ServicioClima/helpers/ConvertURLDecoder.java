package com.challenge.ServicioClima.helpers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConvertURLDecoder {

    public static String codificarURL(String aCodificar){
        String stringCodificado = "";
        try {
            stringCodificado = URLEncoder.encode(aCodificar, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringCodificado;
    }
}
