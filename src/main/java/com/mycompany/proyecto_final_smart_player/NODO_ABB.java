/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class NODO_ABB {
  
    MUSICA musica;
    NODO_ABB hijoIzquierdo;
    NODO_ABB hijoDerecho;

    public NODO_ABB(MUSICA musica) {
        this.musica = musica;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
    
}
