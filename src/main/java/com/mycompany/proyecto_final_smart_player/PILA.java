
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class PILA {
   
     NODOI_PILA cima;

    public PILA() {
        cima = null;
    }

    public void apilar(MUSICA musica) {
        NODOI_PILA nuevo = new NODOI_PILA(musica);

        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public MUSICA bajar() {
        if (cima == null) {
            return null;
        }

        MUSICA musica = cima.musica;
        cima = cima.siguiente;

        return musica;
    }

    public void mostrar() {
        NODOI_PILA aux = cima;

        while (aux != null) {
            System.out.println(aux.musica);
            aux = aux.siguiente;
        }
    }
        public String obtenerHistorial() {
        String texto = "";
        NODOI_PILA aux = cima;

        if (aux == null) {
            return "No hay canciones en el historial";
        }

        while (aux != null) {
            texto = texto + aux.musica.getNombre() + " - " + aux.musica.getArtista() + "\n";
            aux = aux.siguiente;
        }

        return texto;
    }

    public boolean estaVacia() {
        return cima == null;
    }
    
}
