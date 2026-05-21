/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class VARIAS_PLAYS {
    
    PLAYLIST playlists[];
    int contador;

    public VARIAS_PLAYS() {
        playlists = new PLAYLIST[50];
        contador = 0;
    }

    public void crearPlaylist(String nombre) {
        if (contador < playlists.length) {
            playlists[contador] = new PLAYLIST(nombre);
            contador++;
        }
    }

    public PLAYLIST buscarPlaylist(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (playlists[i].getNombre().equalsIgnoreCase(nombre)) {
                return playlists[i];
            }
        }

        return null;
    }

    public void eliminarPlaylist(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (playlists[i].getNombre().equalsIgnoreCase(nombre)) {

                for (int j = i; j < contador - 1; j++) {
                    playlists[j] = playlists[j + 1];
                }

                playlists[contador - 1] = null;
                contador--;
                return;
            }
        }
    }

    public String mostrarPlaylists() {
        String texto = "";

        if (contador == 0) {
            return "No hay playlists creadas";
        }

        for (int i = 0; i < contador; i++) {
            texto = texto + playlists[i].getNombre() + "\n";
        }

        return texto;
    }
    
}
