package com.mycompany.proyecto_final_smart_player;

import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author grija
 */
public class MP3 {
    
   
    MediaPlayer reproductor;
    boolean pausado = false;
    boolean sonando = false;

    public MP3() {
        new JFXPanel();
    }

    public void reproducir(String ruta) {
        try {
            detener();

            File archivo = new File(ruta);
            Media media = new Media(archivo.toURI().toString());

            reproductor = new MediaPlayer(media);
            reproductor.play();

            sonando = true;
            pausado = false;

        } catch (Exception e) {
            System.out.println("No se pudo reproducir la cancion");
            System.out.println(e.getMessage());
        }
    }

    public void pausar() {
        if (reproductor != null && sonando == true) {
            reproductor.pause();
            pausado = true;
            sonando = false;
        }
    }

    public void continuar() {
        if (reproductor != null && pausado == true) {
            reproductor.play();
            pausado = false;
            sonando = true;
        }
    }

    public void playPausa() {
        if (reproductor != null) {
            if (sonando == true) {
                pausar();
            } else {
                continuar();
            }
        }
    }

    public void detener() {
        if (reproductor != null) {
            reproductor.stop();
            reproductor.dispose();
            reproductor = null;
        }

        sonando = false;
        pausado = false;
    }

    public boolean estaSonando() {
        return sonando;
    }

    public boolean estaPausado() {
        return pausado;
    }
}
