/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class Nodo_Doble {
    
    
    MUSICA musica;
    Nodo_Doble siguiente;
    Nodo_Doble anterior;

    public Nodo_Doble(MUSICA musica) {
        this.musica = musica;
        this.siguiente = null;
        this.anterior = null;
    }

    public MUSICA getMusica() {
        return musica;
    }

    public void setMusica(MUSICA musica) {
        this.musica = musica;
    }

    public Nodo_Doble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_Doble siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo_Doble getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo_Doble anterior) {
        this.anterior = anterior;
    }
    
    
}
