package com.mycompany.proyecto_final_smart_player;

import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author grija
 */
public class MP3 {
    
     Player reproductor;

    public void reproducir(String ruta) {
        try {
            FileInputStream archivo = new FileInputStream(ruta);
            reproductor = new Player(archivo);

            Thread hilo = new Thread() {
                public void run() {
                    try {
                        reproductor.play();
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                }
            };

            hilo.start();

        } catch (Exception e) {
            System.out.println("No se encontro");
        }
    }

    public void detener() {
        if (reproductor != null) {
            reproductor.close();
            System.out.println("Musica detenida");
        }
    }
    
}
