package com.mycompany.proyecto_final_smart_player;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author grija
 */
public class ESTADISTICAS {
     String artistas[];
    int contadorArtistas[];

    String generos[];
    int contadorGeneros[];
    int totalArtistas;
    int totalGeneros;

    public ESTADISTICAS() {
        artistas = new String[5000];
        contadorArtistas = new int[5000];

        generos = new String[5000];
        contadorGeneros = new int[5000];

        totalArtistas = 0;
        totalGeneros = 0;
    }

    public void registrarReproduccion(MUSICA musica) {

        if (musica == null) {
            return;
        }

        registrarArtista(musica.getArtista());
        registrarGenero(musica.getGenero());
    }

    public void registrarArtista(String artista) {

        if (artista == null || artista.equals("")) {
            artista = "Desconocido";
        }

        for (int i = 0; i < totalArtistas; i++) {
            if (artistas[i].equalsIgnoreCase(artista)) {
                contadorArtistas[i]++;
                return;
            }
        }

        artistas[totalArtistas] = artista;
        contadorArtistas[totalArtistas] = 1;
        totalArtistas++;
    }

    public void registrarGenero(String genero) {

        if (genero == null || genero.equals("")) {
            genero = "Desconocido";
        }

        for (int i = 0; i < totalGeneros; i++) {
            if (generos[i].equalsIgnoreCase(genero)) {
                contadorGeneros[i]++;
                return;
            }
        }

        generos[totalGeneros] = genero;
        contadorGeneros[totalGeneros] = 1;
        totalGeneros++;
    }

    public String artistaMasEscuchado() {

        if (totalArtistas == 0) {
            return "Todavia no se ha reproducido musica";
        }

        int mayor = 0;
        int posicionMayor = 0;

        for (int i = 0; i < totalArtistas; i++) {
            if (contadorArtistas[i] > mayor) {
                mayor = contadorArtistas[i];
                posicionMayor = i;
            }
        }

        return artistas[posicionMayor] + " (" + mayor + " reproducciones)";
    }

    public String generoMasEscuchado() {

        if (totalGeneros == 0) {
            return "Todavia no se ha reproducido musica";
        }

        int mayor = 0;
        int posicionMayor = 0;

        for (int i = 0; i < totalGeneros; i++) {
            if (contadorGeneros[i] > mayor) {
                mayor = contadorGeneros[i];
                posicionMayor = i;
            }
        }

        return generos[posicionMayor] + " (" + mayor + " reproducciones)";
    }
    public String mostrarHistorialArtistas() {

    if (totalArtistas == 0) {
        return "Todavia no se ha reproducido musica\n";
    }

    String texto = "";

    for (int i = 0; i < totalArtistas; i++) {
        texto = texto + artistas[i] + " - " + contadorArtistas[i] + " reproducciones\n";
    }

    return texto;
}

public String mostrarHistorialGeneros() {

    if (totalGeneros == 0) {
        return "Todavia no se ha reproducido musica\n";
    }

    String texto = "";

    for (int i = 0; i < totalGeneros; i++) {
        texto = texto + generos[i] + " - " + contadorGeneros[i] + " reproducciones\n";
    }

    return texto;
}
    
}
