
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class LISTA_SIMPLE {
    
    
    Nodo_Simple inicio;

    public LISTA_SIMPLE() {
        inicio = null;
    }

    public void agregar(MUSICA musica) {
        Nodo_Simple nuevo = new Nodo_Simple(musica);

        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo_Simple aux = inicio;

            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }

            aux.siguiente = nuevo;
        }
    }

    public void mostrar() {
        Nodo_Simple aux = inicio;

        while (aux != null) {
            System.out.println(aux.musica);
            aux = aux.siguiente;
        }
    }

    public MUSICA buscar(String nombre) {
        Nodo_Simple aux = inicio;

        while (aux != null) {
            if (aux.musica.getNombre().equalsIgnoreCase(nombre)) {
                return aux.musica;
            }

            aux = aux.siguiente;
        }

        return null;
    }

    public void eliminar(String nombre) {
        if (inicio == null) {
            System.out.println("Lista vacia");
            return;
        }

        if (inicio.musica.getNombre().equalsIgnoreCase(nombre)) {
            inicio = inicio.siguiente;
            System.out.println("Cancion eliminada");
            return;
        }

        Nodo_Simple aux = inicio;

        while (aux.siguiente != null) {
            if (aux.siguiente.musica.getNombre().equalsIgnoreCase(nombre)) {
                aux.siguiente = aux.siguiente.siguiente;
                System.out.println("Cancion eliminada");
                return;
            }

            aux = aux.siguiente;
        }

        System.out.println("No se encontro");
    }

    public int contar() {
        int contador = 0;
        Nodo_Simple aux = inicio;

        while (aux != null) {
            contador++;
            aux = aux.siguiente;
        }

        return contador;
    }
}
    

