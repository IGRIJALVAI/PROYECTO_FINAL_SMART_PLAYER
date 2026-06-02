/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class NODO_AVL {
    MUSICA musica;
    NODO_AVL hijoIzquierdo;
    NODO_AVL hijoDerecho;
    int altura;

    public NODO_AVL(MUSICA musica) {
        this.musica = musica;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.altura = 1;
    }

    public MUSICA getMusica() {
        return musica;
    }

    public void setMusica(MUSICA musica) {
        this.musica = musica;
    }

    public NODO_AVL getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NODO_AVL hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NODO_AVL getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NODO_AVL hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    
}
