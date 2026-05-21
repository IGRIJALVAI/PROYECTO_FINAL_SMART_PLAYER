
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class MUSICA {
    

    public String nombre;
    public String artista;
    public String album;
    public String genero;
    public int anio;
    public String duracion;
    public double tamanioMB;
    public String ruta;
    public int reproducciones;

    public MUSICA() {
    }

    public MUSICA(String nombre, String artista, String album, String genero,int anio, String duracion, double tamanioMB, String ruta) {
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.anio = anio;
        this.duracion = duracion;
        this.tamanioMB = tamanioMB;
        this.ruta = ruta;
        this.reproducciones = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public double getTamanioMB() {
        return tamanioMB;
    }

    public void setTamanioMB(double tamanioMB) {
        this.tamanioMB = tamanioMB;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }

    public void aumentarReproduccion() {
        this.reproducciones++;
    }

    
    public String toString() {
        return nombre + " - " + artista;
    }

    
}
