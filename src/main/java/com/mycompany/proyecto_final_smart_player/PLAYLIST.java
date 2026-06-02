/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class PLAYLIST {
      String nombre;
    NODO_PLAY inicio;

    public PLAYLIST(String nombre) {
        this.nombre = nombre;
        this.inicio = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregar(MUSICA musica) {
        NODO_PLAY nuevo = new NODO_PLAY(musica);

        if (inicio == null) {
            inicio = nuevo;
        } else {
            NODO_PLAY aux = inicio;

            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }

            aux.siguiente = nuevo;
        }
    }

    public String mostrar() {
        String texto = "";

        if (inicio == null) {
            return "La playlist esta vacia";
        }

        NODO_PLAY aux = inicio;

        while (aux != null) {
            texto = texto + aux.musica.getNombre() + " - " + aux.musica.getArtista() + "\n";
            aux = aux.siguiente;
        }

        return texto;
    }

    public void eliminar(String nombreCancion) {
        if (inicio == null) {
            return;
        }

        if (inicio.musica.getNombre().equalsIgnoreCase(nombreCancion)) {
            inicio = inicio.siguiente;
            return;
        }

        NODO_PLAY aux = inicio;

        while (aux.siguiente != null) {
            if (aux.siguiente.musica.getNombre().equalsIgnoreCase(nombreCancion)) {
                aux.siguiente = aux.siguiente.siguiente;
                return;
            }

            aux = aux.siguiente;
        }
    }

    public NODO_PLAY getInicio() {
        return inicio;
    }

    public void setInicio(NODO_PLAY inicio) {
        this.inicio = inicio;
    }
    
    
}
