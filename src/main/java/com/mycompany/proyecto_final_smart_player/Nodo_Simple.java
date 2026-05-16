
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class Nodo_Simple {
    
    public MUSICA musica;
    public Nodo_Simple siguiente;
    
     public Nodo_Simple(MUSICA musica) {
        this.musica = musica;
        this.siguiente = null;
    }

    public MUSICA getMusica() {
        return musica;
    }

    public void setMusica(MUSICA musica) {
        this.musica = musica;
    }

    public Nodo_Simple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_Simple siguiente) {
        this.siguiente = siguiente;
    }
    
    
}

