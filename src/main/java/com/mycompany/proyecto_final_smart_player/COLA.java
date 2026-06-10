
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class COLA {
    
    MUSICA musica ;
    
    nodo_coola inicio ;
    nodo_coola fin  ;
    
     public COLA() {
        inicio = null;
        fin = null;
    }

    public void arriba(MUSICA musica) {
        nodo_coola nuevo = new nodo_coola(musica);

        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public MUSICA abajo() {
        if (inicio == null) {
            return null;
        }

        MUSICA musica = inicio.musica;
        inicio = inicio.siguiente;

        if (inicio == null) {
            fin = null;
        }

        return musica;
    }

    public boolean Vacia() {
        return inicio == null;
    }

    public String mostrarCola() {
        String texto = "";
        nodo_coola aux = inicio;

        if (aux == null) {
            return "No hay canciones en la cola";
        }

        while (aux != null) {
            texto = texto + aux.musica.getNombre() + " - " + aux.musica.getArtista() + "\n";
            aux = aux.siguiente;
        }

        return texto;
    }
    
    public void limpiar() {
    inicio = null;
    fin = null;
   }
    public void eliminarPorRuta(String ruta) {

    if (inicio == null) {
        return;
    }

    if (inicio.musica.getRuta().equals(ruta)) {
        inicio = inicio.siguiente;

        if (inicio == null) {
            fin = null;
        }

        return;
    }

    nodo_coola aux = inicio;

    while (aux.siguiente != null) {

        if (aux.siguiente.musica.getRuta().equals(ruta)) {

            aux.siguiente = aux.siguiente.siguiente;

            if (aux.siguiente == null) {
                fin = aux;
            }

            return;
        }

        aux = aux.siguiente;
    }
}
    
}
