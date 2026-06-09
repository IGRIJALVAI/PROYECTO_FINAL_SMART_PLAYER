/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class NODO_HASH {
    
    String clave;
    MUSICA musica;
    NODO_HASH siguiente;

    public NODO_HASH(String clave, MUSICA musica) {
        this.clave = clave;
        this.musica = musica;
        this.siguiente = null;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public MUSICA getMusica() {
        return musica;
    }

    public void setMusica(MUSICA musica) {
        this.musica = musica;
    }

    public NODO_HASH getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NODO_HASH siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
