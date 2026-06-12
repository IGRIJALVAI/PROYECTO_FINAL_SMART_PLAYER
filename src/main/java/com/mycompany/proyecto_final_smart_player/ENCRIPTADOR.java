/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author grija
 */
public class ENCRIPTADOR {
    
     int claveCesar = 3; // de cuantos salstos quieres 
     String claveXOR = "SMART"; // la clave de xor

    public String encriptarCesar(String texto) {
        return aplicarCesar(texto, claveCesar);
    }

    public String desencriptarCesar(String texto) {
        return aplicarCesar(texto, -claveCesar);
    }

    public String aplicarCesar(String texto, int clave) {
        String resultado = "";

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                int pos = c - 'A';
                pos = (pos + clave) % 26;

                if (pos < 0) {
                    pos = pos + 26;
                }

                c = (char) ('A' + pos);
            } else if (c >= 'a' && c <= 'z') {
                int pos = c - 'a';
                pos = (pos + clave) % 26;

                if (pos < 0) {
                    pos = pos + 26;
                }

                c = (char) ('a' + pos);
            }

            resultado = resultado + c;
        }

        return resultado;
    }

    public String encriptarXOR(String texto) {
        byte datos[] = texto.getBytes(StandardCharsets.UTF_8);
        byte clave[] = claveXOR.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < datos.length; i++) {
            datos[i] = (byte) (datos[i] ^ clave[i % clave.length]);
        }

        return Base64.getEncoder().encodeToString(datos);
    }

    public String desencriptarXOR(String texto) {
        try {
            byte datos[] = Base64.getMimeDecoder().decode(texto.trim());
            byte clave[] = claveXOR.getBytes(StandardCharsets.UTF_8);

            for (int i = 0; i < datos.length; i++) {
                datos[i] = (byte) (datos[i] ^ clave[i % clave.length]);
            }

            return new String(datos, StandardCharsets.UTF_8);

        } catch (Exception e) {
            return "";
        }
    }

    public String encriptarInvertido(String texto) {
        String resultado = "";

        for (int i = texto.length() - 1; i >= 0; i--) {
            resultado = resultado + texto.charAt(i);
        }

        return resultado;
    }

    public String desencriptarInvertido(String texto) {
        return encriptarInvertido(texto);
    }

    public String encriptarBase64(String texto) {
        return Base64.getEncoder().encodeToString(texto.getBytes(StandardCharsets.UTF_8));
    }

    public String desencriptarBase64(String texto) {
        try {
            byte bytes[] = Base64.getMimeDecoder().decode(texto.trim());
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }

    public String encriptarAutomatico(String texto) {
        return encriptarInvertido(texto);
    }

    public String desencriptarAutomatico(String textoEncriptado) {

    String resultado;

  
    for (int clave = 1; clave <= 25; clave++) {
        resultado = aplicarCesar(textoEncriptado, -clave);

        if (esPlaylistValida(resultado)) {
            return resultado;
        }
    }

 
    for (int clave = 1; clave <= 25; clave++) {
        resultado = aplicarCesarAscii(textoEncriptado, -clave);

        if (esPlaylistValida(resultado)) {
            return resultado;
        }
    }

 
    resultado = desencriptarXOR(textoEncriptado);
    if (esPlaylistValida(resultado)) {
        return resultado;
    }


    resultado = desencriptarInvertido(textoEncriptado);
    if (esPlaylistValida(resultado)) {
        return resultado;
    }

  
    resultado = desencriptarBase64(textoEncriptado);
    if (esPlaylistValida(resultado)) {
        return resultado;
    }

    return "";
}

  public String aplicarCesarAscii(String texto, int clave) {

    String resultado = "";

    for (int i = 0; i < texto.length(); i++) {
        char c = texto.charAt(i);

        if (c == '\n' || c == '\r') {
            resultado += c;
        } else {
            c = (char) (c + clave);
            resultado += c;
        }
    }

    return resultado;
}

public String encriptarCesarAscii(String texto) {
    return aplicarCesarAscii(texto, claveCesar);
}

public String desencriptarCesarAscii(String texto) {
    return aplicarCesarAscii(texto, -claveCesar);
}
    
    
    public boolean esPlaylistValida(String texto) {

        if (texto == null) {
            return false;
        }
        if (texto.contains("PLAYLIST:")
                && texto.contains("Nombre:")
                && texto.contains("Artista:")
                && texto.contains("Ruta:")) {
            return true;
        }
        return false;
    }
    public String encriptarPorMetodo(String texto, String metodo) {

    if (metodo.equalsIgnoreCase("CESAR")) {
        return encriptarCesar(texto);
    }
     if (metodo.equalsIgnoreCase("CESAR ASCII")) {
        return encriptarCesarAscii(texto);
    }

    if (metodo.equalsIgnoreCase("XOR")) {
        return encriptarXOR(texto);
    }

    if (metodo.equalsIgnoreCase("INVERTIDO")) {
        return encriptarInvertido(texto);
    }

    if (metodo.equalsIgnoreCase("BASE64")) {
        return encriptarBase64(texto);
    }

    return encriptarInvertido(texto);
}
   public String desencriptarFlexible(String textoEncriptado) {

    String resultado;

    resultado = desencriptarAutomatico(textoEncriptado);

    if (!resultado.equals("")) {
        return resultado;
    }

    for (int clave = 1; clave <= 25; clave++) {
        resultado = aplicarCesarAscii(textoEncriptado, -clave);

        if (esListaSimpleValida(resultado)) {
            return resultado;
        }
    }

    return "";
}
    public boolean esListaSimpleValida(String texto) {

    if (texto == null) {
        return false;
    }

    String lineas[] = texto.split("\\R");

    int lineasConTexto = 0;

    for (int i = 0; i < lineas.length; i++) {
        if (!lineas[i].trim().equals("")) {
            lineasConTexto++;
        }
    }

    if (lineasConTexto < 2) {
        return false;
    }

   
    if (!texto.contains(" ")) {  // Una lista simple desencriptada debe tener espacios normales
        return false;
    }

   
    if (texto.contains("#") || texto.contains("|") || texto.contains("\\") || texto.contains("\"")) {  // Si todavia tiene simbolos raros  no la aceptamos
        return false;
    }

    return true;
}
    
}
