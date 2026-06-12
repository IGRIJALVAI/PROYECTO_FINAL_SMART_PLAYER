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
public class TABLA_HASH {
    NODO_HASH tabla[];
    int tamano;

    public TABLA_HASH(int tamano) {
        this.tamano = tamano;
        tabla = new NODO_HASH[tamano];
    }

    public String limpiarClave(String clave) {
        if (clave == null || clave.trim().equals("")) {
            return "desconocido";
        }

        return clave.trim().toLowerCase();
    }

    public int funcionHash(String clave) {

        int suma = 0;

        clave = limpiarClave(clave);

        for (int i = 0; i < clave.length(); i++) {
            suma = suma + clave.charAt(i);
        }

        return suma % tamano;
    }

    public void insertar(String clave, MUSICA musica) {

        clave = limpiarClave(clave);

        int posicion = funcionHash(clave);

        NODO_HASH nuevo = new NODO_HASH(clave, musica);

        if (tabla[posicion] == null) {
            tabla[posicion] = nuevo;
        } else {
            NODO_HASH aux = tabla[posicion];

            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }

            aux.siguiente = nuevo;
        }
    }

    public String buscarExacto(String clave) {

        clave = limpiarClave(clave);

        int posicion = funcionHash(clave);

        NODO_HASH aux = tabla[posicion];

        String resultado = "";

        while (aux != null) {

            if (aux.clave.equals(clave)) {
                resultado = resultado + datosMusica(aux.musica);
            }

            aux = aux.siguiente;
        }

        if (resultado.equals("")) {
            return "No se encontraron resultados exactos";
        }

        return resultado;
    }

    public String buscarParecido(String texto) {

        texto = limpiarClave(texto);

        String resultado = "";

        for (int i = 0; i < tamano; i++) {

            NODO_HASH aux = tabla[i];

            while (aux != null) {

                if (aux.clave.contains(texto)) {
                    resultado = resultado + datosMusica(aux.musica);
                }

                aux = aux.siguiente;
            }
        }

        if (resultado.equals("")) {
            return "No se encontraron resultados";
        }

        return resultado;
    }

    public String datosMusica(MUSICA musica) {

        String texto = "";

        texto = texto
                + "Nombre: " + musica.getNombre()
                + "\nArtista: " + musica.getArtista()
                + "\nAlbum: " + musica.getAlbum()
                + "\nGenero: " + musica.getGenero()
                + "\nAño: " + musica.getAnio()
                + "\nDuracion: " + musica.getDuracion()
                + "\nRuta: " + musica.getRuta()
                + "\n-----------------------------\n";

        return texto;
    }
    public void llenarTablaParecido(String texto, DefaultTableModel modelo) {

    texto = limpiarClave(texto);

    modelo.setRowCount(0);

    for (int i = 0; i < tamano; i++) {

        NODO_HASH aux = tabla[i];

        while (aux != null) {

            if (aux.clave.contains(texto)) {

                Object fila[] = new Object[8];

                fila[0] = aux.musica.getNombre();
                fila[1] = aux.musica.getArtista();
                fila[2] = aux.musica.getAlbum();
                fila[3] = aux.musica.getGenero();
                fila[4] = aux.musica.getAnio();
                fila[5] = aux.musica.getDuracion();
                fila[6] = aux.musica.getTamanioMB();
                fila[7] = aux.musica.getRuta();

                modelo.addRow(fila);
            }

            aux = aux.siguiente;
        }
    }
}
    
    
}
