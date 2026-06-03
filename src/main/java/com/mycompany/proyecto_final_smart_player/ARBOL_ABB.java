/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author grija
 */
public class ARBOL_ABB {
      NODO_ABB raiz;

    public ARBOL_ABB() {
        this.raiz = null;
    }

    public boolean EstaVacio() {
        if (raiz == null) {
            return true;
        }
        return false;
    }

    public void AgregarNodo(MUSICA musica) {

        NODO_ABB nuevo = new NODO_ABB(musica);

        if (raiz == null) {
            raiz = nuevo;
        } else {

            NODO_ABB auxiliar = raiz;
            NODO_ABB papa;

            while (true) {

                papa = auxiliar;

                if (musica.getNombre().compareToIgnoreCase(auxiliar.musica.getNombre()) < 0) {

                    auxiliar = auxiliar.hijoIzquierdo;

                    if (auxiliar == null) {
                        papa.hijoIzquierdo = nuevo;
                        return;
                    }

                } else {

                    auxiliar = auxiliar.hijoDerecho;

                    if (auxiliar == null) {
                        papa.hijoDerecho = nuevo;
                        return;
                    }
                }
            }
        }
    }

    public void Inorden(NODO_ABB r) {

        if (r != null) {
            Inorden(r.hijoIzquierdo);
            System.out.println(r.musica.getNombre() + " - " + r.musica.getArtista());
            Inorden(r.hijoDerecho);
        }
    }

    public void PreOrden(NODO_ABB r) {

        if (r != null) {
            System.out.println(r.musica.getNombre() + " - " + r.musica.getArtista());
            PreOrden(r.hijoIzquierdo);
            PreOrden(r.hijoDerecho);
        }
    }

    public void PosOrden(NODO_ABB r) {

        if (r != null) {
            PosOrden(r.hijoIzquierdo);
            PosOrden(r.hijoDerecho);
            System.out.println(r.musica.getNombre() + " - " + r.musica.getArtista());
        }
    }

    public MUSICA buscarNodo(String nombre) {

        NODO_ABB aux = raiz;

        while (aux != null && !aux.musica.getNombre().equalsIgnoreCase(nombre)) {

            if (nombre.compareToIgnoreCase(aux.musica.getNombre()) < 0) {
                aux = aux.hijoIzquierdo;
            } else {
                aux = aux.hijoDerecho;
            }
        }

        if (aux == null) {
            return null;
        } else {
            return aux.musica;
        }
    }

    public NODO_ABB getRaiz() {
        return raiz;
    }
    
    public void llenarTablaInorden(NODO_ABB r, DefaultTableModel modelo) {

    if (r != null) {

        llenarTablaInorden(r.hijoIzquierdo, modelo);

        Object fila[] = new Object[8];

        fila[0] = r.musica.getNombre();
        fila[1] = r.musica.getArtista();
        fila[2] = r.musica.getAlbum();
        fila[3] = r.musica.getGenero();
        fila[4] = r.musica.getAnio();
        fila[5] = r.musica.getDuracion();
        fila[6] = r.musica.getTamanioMB();
        fila[7] = r.musica.getRuta();

        modelo.addRow(fila);

        llenarTablaInorden(r.hijoDerecho, modelo);
    }
}
    public void llenarListaDobleInorden(NODO_ABB r, LISTA_DOBLE lista) {

    if (r != null) {

        llenarListaDobleInorden(r.hijoIzquierdo, lista);

        lista.agregar(r.musica);

        llenarListaDobleInorden(r.hijoDerecho, lista);
    }
}
    
}
