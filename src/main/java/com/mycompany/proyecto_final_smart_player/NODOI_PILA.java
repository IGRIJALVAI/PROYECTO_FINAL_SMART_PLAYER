
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class NODOI_PILA {
    
    MUSICA musica;
    NODOI_PILA siguiente;

    public NODOI_PILA(MUSICA musica) {
        this.musica = musica;
        this.siguiente = null;
    }

    public MUSICA getMusica() {
        return musica;
    }

    public void setMusica(MUSICA musica) {
        this.musica = musica;
    }

    public NODOI_PILA getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NODOI_PILA siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
