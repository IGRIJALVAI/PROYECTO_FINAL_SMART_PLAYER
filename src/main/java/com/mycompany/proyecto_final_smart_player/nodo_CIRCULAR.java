package com.mycompany.proyecto_final_smart_player;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author grija
 */
public class nodo_CIRCULAR {
    
    MUSICA musica;
    nodo_CIRCULAR siguiente;

    public nodo_CIRCULAR(MUSICA musica) {
        this.musica = musica;
        this.siguiente = null;
    }

    public MUSICA getMusica() {
        return musica;
    }

    public void setMusica(MUSICA musica) {
        this.musica = musica;
    }

    public nodo_CIRCULAR getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nodo_CIRCULAR siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
