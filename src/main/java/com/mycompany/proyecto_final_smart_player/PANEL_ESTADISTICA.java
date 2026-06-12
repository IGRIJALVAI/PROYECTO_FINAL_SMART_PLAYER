/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;

/**
 *
 * @author grija
 */
public class PANEL_ESTADISTICA extends javax.swing.JPanel {

    /**
     * Creates new form PANEL_ESTADISTICA
     */
    RESPRODUCTORMP3 principal;
    public PANEL_ESTADISTICA() {
        initComponents();
    }
    public PANEL_ESTADISTICA(RESPRODUCTORMP3 principal) {
    initComponents();
    this.principal = principal;
    TXT_ACTU.setEditable(false);
}

    
    public void actualizarEstadisticas() {

    double tiempoABBms = principal.tiempoCargaABB / 1000000.0;
    double tiempoAVLms = principal.tiempoCargaAVL / 1000000.0;

    String artistaMasRepetido = obtenerArtistaMasRepetido();
    String generoMasRepetido = obtenerGeneroMasRepetido();
    String artistaMasEscuchado = principal.estadisticasReproduccion.artistaMasEscuchado();
    String generoMasEscuchado = principal.estadisticasReproduccion.generoMasEscuchado();
    String historialArtistas = principal.estadisticasReproduccion.mostrarHistorialArtistas();
    String historialGeneros = principal.estadisticasReproduccion.mostrarHistorialGeneros();

    TXT_ACTU.setText(
            "ESTADISTICAS DEL REPRODUCTOR\n"
            + "\n\n"
            + "CANCIONES\n"
            + "Total de canciones" + principal.totalCancionesCargadas + "\n\n"

            + "TIEMPOS DE CARGA\n"
            + "Tiempo ABB: " + tiempoABBms + " ms\n"
            + "Tiempo AVL: " + tiempoAVLms + " ms\n\n"
                    
            + "ARTISTAS ESCUCHADOS\n"
            + "\n"
            + historialArtistas + "\n"

            + "GENEROS ESCUCHADOS\n"
            + "\n"
            + historialGeneros + "\n"
                    
            + "MAS ESCUCHADOS\n"
            + "Artista mas escuchado: " + artistaMasEscuchado + "\n"
            + "Genero mas escuchado: " + generoMasEscuchado + "\n\n"

            + "DATOS MAS REPETIDOS\n"
            + "Artista mas repetido: " + artistaMasRepetido + "\n"
            + "Genero mas repetido: " + generoMasRepetido + "\n\n"

            + "PLAYLISTS\n"
            + "Playlists creadas: " + principal.VariosPlaylist.contador + "\n"
            + obtenerDatosPlaylists() + "\n"

            + "ESTRUCTURAS IMPLEMENTADAS\n"
            + "ABB: Organiza canciones por nombre\n"
            + "AVL: Organiza canciones de forma balanceada\n"
            + "Tabla Hash: Busqueda rapida por cancion, artista y genero\n"
            + "Lista Simple: Biblioteca general\n"
            + "Lista Doble: Anterior y siguiente\n"
            + "Lista Circular: Reproduccion circular\n"
            + "Pila: Historial de canciones reproducidas\n"
            + "Cola: Cola de reproduccion\n"
    );
}
    
    
    
    public String obtenerArtistaMasRepetido() {

    if (principal.listaMusica.inicio == null) {
        return "No hay canciones cargadas";
    }
    String artistaMayor = "";
    int mayor = 0;
    Nodo_Simple aux1 = principal.listaMusica.inicio;
    while (aux1 != null) {
        String artistaActual = aux1.musica.getArtista();
        int contador = 0;
        Nodo_Simple aux2 = principal.listaMusica.inicio;
        while (aux2 != null) {
            if (aux2.musica.getArtista().equalsIgnoreCase(artistaActual)) {
                contador++;
            }
            aux2 = aux2.siguiente;
        }
        if (contador > mayor) {
            mayor = contador;
            artistaMayor = artistaActual;
        }
        aux1 = aux1.siguiente;
    }
    return artistaMayor + " (" + mayor + " canciones)";
}
    
    
    public String obtenerGeneroMasRepetido() {
    if (principal.listaMusica.inicio == null) {
        return "No hay canciones cargadas";
    }
    String generoMayor = "";
    int mayor = 0;
    Nodo_Simple aux1 = principal.listaMusica.inicio;
    while (aux1 != null) {
        String generoActual = aux1.musica.getGenero();
        int contador = 0;
        Nodo_Simple aux2 = principal.listaMusica.inicio;
        while (aux2 != null) {
            if (aux2.musica.getGenero().equalsIgnoreCase(generoActual)) {
                contador++;
            }
            aux2 = aux2.siguiente;
        }
        if (contador > mayor) {
            mayor = contador;
            generoMayor = generoActual;
        }
        aux1 = aux1.siguiente;
    }
    return generoMayor + " (" + mayor + " canciones)";
}
    
    
    
    
    public String obtenerDatosPlaylists() {
    String texto = "";
    if (principal.VariosPlaylist.contador == 0) {
        return "No hay playlists creadas\n";
    }
    for (int i = 0; i < principal.VariosPlaylist.contador; i++) {
        PLAYLIST playlist = principal.VariosPlaylist.playlists[i];
        int cantidad = contarCancionesPlaylist(playlist);
        texto = texto
                + "Playlist: " + playlist.getNombre()
                + " - Canciones: " + cantidad + "\n";
    }

    return texto;
}
    
    public int contarCancionesPlaylist(PLAYLIST playlist) {
    int contador = 0;
    NODO_PLAY aux = playlist.inicio;
    while (aux != null) {
        contador++;
        aux = aux.siguiente;
    }
    return contador;
}
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTN_ACTUALIZAR = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TXT_ACTU = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        BTN_VER_ABB = new javax.swing.JButton();
        BTN_VER_AVL = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));

        BTN_ACTUALIZAR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        BTN_ACTUALIZAR.setBorderPainted(false);
        BTN_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ACTUALIZARActionPerformed(evt);
            }
        });

        TXT_ACTU.setBackground(new java.awt.Color(0, 0, 0));
        TXT_ACTU.setColumns(20);
        TXT_ACTU.setRows(5);
        jScrollPane1.setViewportView(TXT_ACTU);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("ESTADISTICAS");

        BTN_VER_ABB.setBackground(new java.awt.Color(0, 0, 0));
        BTN_VER_ABB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/AVL.png"))); // NOI18N
        BTN_VER_ABB.setBorderPainted(false);
        BTN_VER_ABB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VER_ABBActionPerformed(evt);
            }
        });

        BTN_VER_AVL.setBackground(new java.awt.Color(0, 0, 0));
        BTN_VER_AVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/AVL2.png"))); // NOI18N
        BTN_VER_AVL.setBorderPainted(false);
        BTN_VER_AVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VER_AVLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_ACTUALIZAR)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN_VER_AVL)
                            .addComponent(BTN_VER_ABB))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTN_ACTUALIZAR)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(BTN_VER_ABB)
                        .addGap(30, 30, 30)
                        .addComponent(BTN_VER_AVL))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ACTUALIZARActionPerformed
        // TODO add your handling code here:
          actualizarEstadisticas();
    }//GEN-LAST:event_BTN_ACTUALIZARActionPerformed

    private void BTN_VER_ABBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VER_ABBActionPerformed
        // TODO add your handling code here:
        
         VISOR_JGRAPHX visor = new VISOR_JGRAPHX();
         visor.mostrarABB(principal.arbolABB.getRaiz());
        
    }//GEN-LAST:event_BTN_VER_ABBActionPerformed

    private void BTN_VER_AVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VER_AVLActionPerformed
        // TODO add your handling code here:
         VISOR_JGRAPHX visor = new VISOR_JGRAPHX();
         visor.mostrarAVL(principal.arbolAVL.getRaiz());
    }//GEN-LAST:event_BTN_VER_AVLActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_ACTUALIZAR;
    private javax.swing.JButton BTN_VER_ABB;
    private javax.swing.JButton BTN_VER_AVL;
    private javax.swing.JTextArea TXT_ACTU;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
