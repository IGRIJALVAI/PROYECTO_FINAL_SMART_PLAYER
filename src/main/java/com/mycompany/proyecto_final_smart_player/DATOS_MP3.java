
package com.mycompany.proyecto_final_smart_player;


/**
 *
 * @author grija
 */

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.io.File;

public class DATOS_MP3 {

    public MUSICA leerDatos(String ruta) {

        String nombre = "Desconocido";
        String artista = "Desconocido";
        String album = "Desconocido";
        String genero = "Desconocido";
        int anio = 0;
        String duracion = "Desconocida";
        double tamanioMB = 0;

        try {
            File archivo = new File(ruta);

            nombre = archivo.getName();
            tamanioMB = archivo.length() / 1024.0 / 1024.0;

            Mp3File mp3 = new Mp3File(ruta);

            int segundos = (int) mp3.getLengthInSeconds();
            int minutos = segundos / 60;
            int seg = segundos % 60;

            duracion = minutos + ":" + String.format("%02d", seg);

            if (mp3.hasId3v2Tag()) {

                ID3v2 tag = mp3.getId3v2Tag();

                if (tag.getTitle() != null && !tag.getTitle().equals("")) {
                    nombre = tag.getTitle();
                }

                if (tag.getArtist() != null && !tag.getArtist().equals("")) {
                    artista = tag.getArtist();
                }

                if (tag.getAlbum() != null && !tag.getAlbum().equals("")) {
                    album = tag.getAlbum();
                }

                if (tag.getGenreDescription() != null && !tag.getGenreDescription().equals("")) {
                    genero = tag.getGenreDescription();
                }

                if (tag.getYear() != null && !tag.getYear().equals("")) {
                    try {
                        anio = Integer.parseInt(tag.getYear());
                    } catch (Exception e) {
                        anio = 0;
                    }
                }

            } else if (mp3.hasId3v1Tag()) {

                ID3v1 tag = mp3.getId3v1Tag();

                if (tag.getTitle() != null && !tag.getTitle().equals("")) {
                    nombre = tag.getTitle();
                }

                if (tag.getArtist() != null && !tag.getArtist().equals("")) {
                    artista = tag.getArtist();
                }

                if (tag.getAlbum() != null && !tag.getAlbum().equals("")) {
                    album = tag.getAlbum();
                }

                if (tag.getGenreDescription() != null && !tag.getGenreDescription().equals("")) {
                    genero = tag.getGenreDescription();
                }

                if (tag.getYear() != null && !tag.getYear().equals("")) {
                    try {
                        anio = Integer.parseInt(tag.getYear());
                    } catch (Exception e) {
                        anio = 0;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer datos del MP3: " + ruta);
            System.out.println(e.getMessage());
        }

        MUSICA musica = new MUSICA(nombre, artista, album, genero, anio, duracion, tamanioMB, ruta);

        return musica;
    }
}