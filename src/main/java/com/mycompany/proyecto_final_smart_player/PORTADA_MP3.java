/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author grija
 */
public class PORTADA_MP3 {
    public void mostrarPortada(String ruta, JLabel label) {

        label.setIcon(null);
        label.setText("Sin portada");
        label.setHorizontalAlignment(JLabel.CENTER);

        try {
            Mp3File mp3 = new Mp3File(ruta);

            if (mp3.hasId3v2Tag()) {

                ID3v2 tag = mp3.getId3v2Tag();

                byte imagen[] = tag.getAlbumImage();

                if (imagen != null) {

                    ByteArrayInputStream entrada = new ByteArrayInputStream(imagen);

                    Image imagenOriginal = ImageIO.read(entrada);

                    if (imagenOriginal != null) {

                        int ancho = label.getWidth();
                        int alto = label.getHeight();

                        if (ancho <= 0) {
                            ancho = 180;
                        }

                        if (alto <= 0) {
                            alto = 180;
                        }

                        Image imagenEscalada = imagenOriginal.getScaledInstance(
                                ancho,
                                alto,
                                Image.SCALE_SMOOTH
                        );

                        label.setText("");
                        label.setIcon(new ImageIcon(imagenEscalada));
                    }
                }
            }

        } catch (Exception e) {
            label.setIcon(null);
            label.setText("Sin portada");
            System.out.println("No se pudo cargar la portada");
            System.out.println(e.getMessage());
        }
    }
    
}
