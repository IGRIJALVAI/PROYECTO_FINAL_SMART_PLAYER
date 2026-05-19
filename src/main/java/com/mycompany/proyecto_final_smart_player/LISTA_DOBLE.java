/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class LISTA_DOBLE {
    
    Nodo_Doble inicio;
    Nodo_Doble fin;
    Nodo_Doble actual;

    public LISTA_DOBLE() {
        inicio = null;
        fin = null;
        actual = null;
    }

    public void agregar(MUSICA musica) {
        Nodo_Doble nuevo = new Nodo_Doble(musica);

        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
            actual = nuevo;
        } else {
            fin.siguiente = nuevo;
            nuevo.anterior = fin;
            fin = nuevo;
        }
    }

    public MUSICA siguiente() {
        if (actual == null) {
            return null;
        }

        if (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        return actual.musica;
    }

    public MUSICA anterior() {
        if (actual == null) {
            return null;
        }

        if (actual.anterior != null) {
            actual = actual.anterior;
        }

        return actual.musica;
    }

    public void ponerActual(String ruta) { //porla ruta
        Nodo_Doble aux = inicio;

        while (aux != null) {
            if (aux.musica.getRuta().equals(ruta)) {
                actual = aux;
                return;
            }

            aux = aux.siguiente;
        }
    }

    public void mostrar() {
        Nodo_Doble aux = inicio;

        while (aux != null) {
            System.out.println(aux.musica);
            aux = aux.siguiente;
        }
    }
    
}
