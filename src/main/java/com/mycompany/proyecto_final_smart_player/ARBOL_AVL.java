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
public class ARBOL_AVL {
    NODO_AVL raiz;

    public ARBOL_AVL() {
        raiz = null;
    }

    public boolean EstaVacio() {
        if (raiz == null) {
            return true;
        }
        return false;
    }

    public int altura(NODO_AVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    public int mayor(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public int comparar(MUSICA a, MUSICA b) {
        int resultado = a.getNombre().compareToIgnoreCase(b.getNombre());

        if (resultado == 0) {
            resultado = a.getRuta().compareToIgnoreCase(b.getRuta());
        }

        return resultado;
    }

    public int obtenerBalance(NODO_AVL nodo) {
        if (nodo == null) {
            return 0;
        }

        return altura(nodo.hijoIzquierdo) - altura(nodo.hijoDerecho);
    }

    public NODO_AVL rotacionDerecha(NODO_AVL y) {

        NODO_AVL x = y.hijoIzquierdo;
        NODO_AVL temp = x.hijoDerecho;

        x.hijoDerecho = y;
        y.hijoIzquierdo = temp;

        y.altura = mayor(altura(y.hijoIzquierdo), altura(y.hijoDerecho)) + 1;
        x.altura = mayor(altura(x.hijoIzquierdo), altura(x.hijoDerecho)) + 1;

        return x;
    }

    public NODO_AVL rotacionIzquierda(NODO_AVL x) {

        NODO_AVL y = x.hijoDerecho;
        NODO_AVL temp = y.hijoIzquierdo;

        y.hijoIzquierdo = x;
        x.hijoDerecho = temp;

        x.altura = mayor(altura(x.hijoIzquierdo), altura(x.hijoDerecho)) + 1;
        y.altura = mayor(altura(y.hijoIzquierdo), altura(y.hijoDerecho)) + 1;

        return y;
    }

    public void AgregarNodo(MUSICA musica) {
        raiz = insertar(raiz, musica);
    }

    public NODO_AVL insertar(NODO_AVL nodo, MUSICA musica) {

        if (nodo == null) {
            return new NODO_AVL(musica);
        }

        int comparacion = comparar(musica, nodo.musica);

        if (comparacion < 0) {
            nodo.hijoIzquierdo = insertar(nodo.hijoIzquierdo, musica);
        } else if (comparacion > 0) {
            nodo.hijoDerecho = insertar(nodo.hijoDerecho, musica);
        } else {
            return nodo;
        }

        nodo.altura = 1 + mayor(altura(nodo.hijoIzquierdo), altura(nodo.hijoDerecho));

        int balance = obtenerBalance(nodo);

        if (balance > 1 && comparar(musica, nodo.hijoIzquierdo.musica) < 0) {
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && comparar(musica, nodo.hijoDerecho.musica) > 0) {
            return rotacionIzquierda(nodo);
        }

        if (balance > 1 && comparar(musica, nodo.hijoIzquierdo.musica) > 0) {
            nodo.hijoIzquierdo = rotacionIzquierda(nodo.hijoIzquierdo);
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && comparar(musica, nodo.hijoDerecho.musica) < 0) {
            nodo.hijoDerecho = rotacionDerecha(nodo.hijoDerecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public MUSICA buscarNodo(String nombre) {

       if (nombre == null) {
        return null;
    }

    nombre = nombre.trim();

    NODO_AVL aux = raiz;

    while (aux != null) {

        String nombreNodo = aux.musica.getNombre().trim();

        if (nombre.equalsIgnoreCase(nombreNodo)) {
            return aux.musica;
        }

        if (nombre.compareToIgnoreCase(nombreNodo) < 0) {
            aux = aux.hijoIzquierdo;
        } else {
            aux = aux.hijoDerecho;
        }
    }

    return null;
    }

    public void llenarTablaInorden(NODO_AVL r, DefaultTableModel modelo) {

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

    public NODO_AVL getRaiz() {
        return raiz;
    }
    
    public String recorridoInOrden() {
    return InOrdenTexto(raiz);
}

public String recorridoPreOrden() {
    return PreOrdenTexto(raiz);
}

public String recorridoPostOrden() {
    return PostOrdenTexto(raiz);
}

public String InOrdenTexto(NODO_AVL r) {
    String texto = "";

    if (r != null) {
        texto = texto + InOrdenTexto(r.hijoIzquierdo);
        texto = texto + r.musica.getNombre() + " - " + r.musica.getArtista() + "\n";
        texto = texto + InOrdenTexto(r.hijoDerecho);
    }

    return texto;
}

public String PreOrdenTexto(NODO_AVL r) {
    String texto = "";

    if (r != null) {
        texto = texto + r.musica.getNombre() + " - " + r.musica.getArtista() + "\n";
        texto = texto + PreOrdenTexto(r.hijoIzquierdo);
        texto = texto + PreOrdenTexto(r.hijoDerecho);
    }

    return texto;
}

public String PostOrdenTexto(NODO_AVL r) {
    String texto = "";

    if (r != null) {
        texto = texto + PostOrdenTexto(r.hijoIzquierdo);
        texto = texto + PostOrdenTexto(r.hijoDerecho);
        texto = texto + r.musica.getNombre() + " - " + r.musica.getArtista() + "\n";
    }

    return texto;
}
    
    
    public String buscarParecido(String texto) {
    return buscarParecidoRec(raiz, texto.toLowerCase());
}

public String buscarParecidoRec(NODO_AVL r, String texto) {

    String resultado = "";

    if (r != null) {

        resultado = resultado + buscarParecidoRec(r.hijoIzquierdo, texto);

        if (r.musica.getNombre().toLowerCase().contains(texto)) {
            resultado = resultado
                    + r.musica.getNombre() + " - "
                    + r.musica.getArtista() + "\n";
        }

        resultado = resultado + buscarParecidoRec(r.hijoDerecho, texto);
    }

    return resultado;
}
    
    
    
    
    
    
}
