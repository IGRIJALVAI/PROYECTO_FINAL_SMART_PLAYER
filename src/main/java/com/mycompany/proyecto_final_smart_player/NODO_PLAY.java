/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class NODO_PLAY {
    
      public  MUSICA musica;
   public NODO_PLAY siguiente;

    public NODO_PLAY(MUSICA musica) {
        this.musica = musica;
        this.siguiente = null;
    }

    public MUSICA getMusica() {
        return musica;
    }

    public void setMusica(MUSICA musica) {
        this.musica = musica;
    }

    public NODO_PLAY getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NODO_PLAY siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
