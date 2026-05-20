/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class LISTA_CIRCULAR {
    nodo_CIRCULAR inicio;
    nodo_CIRCULAR fin;
    nodo_CIRCULAR actual;

    public LISTA_CIRCULAR() {
        inicio = null;
        fin = null;
        actual = null;
    }

    public void agregar(MUSICA musica) {
        nodo_CIRCULAR nuevo = new nodo_CIRCULAR(musica);

        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
            fin.siguiente = inicio;
            actual = inicio;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
            fin.siguiente = inicio;
        }
    }

    public MUSICA siguiente() {
        if (actual == null) {
            return null;
        }

        MUSICA musica = actual.musica;
        actual = actual.siguiente;

        return musica;
    }

    public void ponerPorRuta(String ruta) {
        if (inicio == null) {
            return;
        }

        nodo_CIRCULAR aux = inicio;

        do {
            if (aux.musica.getRuta().equals(ruta)) {
                actual = aux;
                return;
            }

            aux = aux.siguiente;

        } while (aux != inicio);
    }

    public String mostrar() {
        String texto = "";

        if (inicio == null) {
            return "No hay canciones en la lista circular";
        }

        nodo_CIRCULAR aux = inicio;

        do {
            texto = texto + aux.musica.getNombre() + " - " + aux.musica.getArtista() + "\n";
            aux = aux.siguiente;

        } while (aux != inicio);

        return texto;
    }
    
}
