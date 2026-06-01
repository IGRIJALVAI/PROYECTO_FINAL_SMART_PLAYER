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

        } catch (Exception e) {
            System.out.println("No se pudo reproducir la cancion");
            System.out.println(e.getMessage());
        }
    }

    public void pausar() {
        if (reproductor != null) {
            reproductor.pause();
        }
    }

    public void continuar() {
        if (reproductor != null) {
            reproductor.play();
        }
    }

    public void detener() {
        if (reproductor != null) {
            reproductor.stop();
            reproductor.dispose();
            reproductor = null;
        }
    }   
}
