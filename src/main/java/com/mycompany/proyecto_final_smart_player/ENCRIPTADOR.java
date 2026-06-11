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
    
     int claveCesar = 3;
    String claveXOR = "SMART";

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
    
    
    
}
