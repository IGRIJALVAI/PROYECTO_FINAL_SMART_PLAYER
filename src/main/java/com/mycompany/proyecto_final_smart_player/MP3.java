package com.mycompany.proyecto_final_smart_player;

import java.io.BufferedInputStream;
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
            detener(); //PRIMERO LLAMANMOS A DETENER PARA QUE SE CIERRE CUALQUIER MUSICA Y SONAR LA OTRA
            

            FileInputStream archivo = new FileInputStream(ruta);
            BufferedInputStream buffer = new BufferedInputStream(archivo);

            reproductor = new Player(buffer);

            Thread hilo = new Thread() {
                public void run() {
                    try {
                        reproductor.play();
                    } catch (Exception e) {
                        System.out.println("Error al reproducir");
                    }
                }
            };

            hilo.start();

        } catch (Exception e) {
            System.out.println("No se pudo reproducir la cancion");
        }
    }

    public void detener() { 

        if (reproductor != null) {
            reproductor.close();
            reproductor = null;
        }
    }
    
}
