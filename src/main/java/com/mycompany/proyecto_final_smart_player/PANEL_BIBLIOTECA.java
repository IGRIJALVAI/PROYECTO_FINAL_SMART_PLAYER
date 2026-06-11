
package com.mycompany.proyecto_final_smart_player;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author grija
 */
public class PANEL_BIBLIOTECA extends javax.swing.JPanel {

    /**
     * Creates new form PANEL_BIBLIOTECA
     */


     RESPRODUCTORMP3 principal;

        public PANEL_BIBLIOTECA() {
               initComponents();
           }

    public PANEL_BIBLIOTECA(RESPRODUCTORMP3 principal) {
        initComponents();
        this.principal = principal;
        String columnas[] = {"Nombre", "Artista", "Album", "Genero", "Año", "Duracion", "Tamaño", "Ruta"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        tablaMusica.setModel(modelo);
             estiloTablaSpotify();
    }
    
    private void estiloTablaSpotify() {

    tablaMusica.setBackground(new Color(0, 0, 0));
    tablaMusica.setForeground(Color.WHITE);
    tablaMusica.setGridColor(new Color(35, 35, 35));

    
    tablaMusica.setSelectionBackground(new Color(180, 0, 0)); // cuadno se oprime se pone rojo

    tablaMusica.setRowHeight(35);
    tablaMusica.setShowGrid(true);
    tablaMusica.setOpaque(true);
    tablaMusica.setFillsViewportHeight(true);

    jScrollPane1.getViewport().setBackground(new Color(0, 0, 0));
    jScrollPane1.setBackground(new Color(0, 0, 0));
    jScrollPane1.setBorder(null);

    JTableHeader header = tablaMusica.getTableHeader();
    header.setBackground(new Color(15, 15, 15));
    header.setForeground(Color.WHITE);
    header.setOpaque(true);

    header.setDefaultRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            label.setBackground(new Color(15, 15, 15));
            label.setForeground(Color.RED);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setOpaque(true);

            return label;
        }
    });

    DefaultTableCellRenderer render = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                c.setBackground(new Color(180, 0, 0)); // Rojo 
                c.setForeground(Color.WHITE);
            } else {
                c.setBackground(new Color(0, 0, 0));   // fondo negro
                c.setForeground(Color.WHITE);
            }

            return c;
        }
    };

    for (int i = 0; i < tablaMusica.getColumnCount(); i++) {
        tablaMusica.getColumnModel().getColumn(i).setCellRenderer(render);
    }
}
   public void cargarMusicaDeCarpeta(java.io.File carpeta) {

  java.io.File archivos[] = carpeta.listFiles();

    if (archivos != null) {

        for (int i = 0; i < archivos.length; i++) {

            if (archivos[i].isDirectory()) {

                cargarMusicaDeCarpeta(archivos[i]);

            } else {

                String nombreArchivo = archivos[i].getName().toLowerCase();

                if (nombreArchivo.endsWith(".mp3")) {

                    String ruta = archivos[i].getAbsolutePath();

                   DATOS_MP3 datos = new DATOS_MP3();
                    MUSICA musica = datos.leerDatos(ruta);

                    long inicioABB = System.nanoTime();
                    principal.arbolABB.AgregarNodo(musica);
                    long finABB = System.nanoTime();

                    principal.tiempoCargaABB = principal.tiempoCargaABB + (finABB - inicioABB);

                    long inicioAVL = System.nanoTime();
                    principal.arbolAVL.AgregarNodo(musica);
                    long finAVL = System.nanoTime();

                    principal.tiempoCargaAVL = principal.tiempoCargaAVL + (finAVL - inicioAVL);
                    
                    principal.hashArtista.insertar(musica.getArtista(), musica);
                    principal.hashGenero.insertar(musica.getGenero(), musica);

                    principal.listaMusica.agregar(musica);
                    principal.listaCircular.agregar(musica);

                    principal.totalCancionesCargadas++;
                }
            }
        }
    }
}

public MUSICA obtenerMusicaDeTabla(int fila) {

    String nombre = tablaMusica.getValueAt(fila, 0).toString();
    String artista = tablaMusica.getValueAt(fila, 1).toString();
    String album = tablaMusica.getValueAt(fila, 2).toString();
    String genero = tablaMusica.getValueAt(fila, 3).toString();
    int anio = Integer.parseInt(tablaMusica.getValueAt(fila, 4).toString());
    String duracion = tablaMusica.getValueAt(fila, 5).toString();
    double tamanio = Double.parseDouble(tablaMusica.getValueAt(fila, 6).toString());
    String ruta = tablaMusica.getValueAt(fila, 7).toString();

    MUSICA musica = new MUSICA(nombre, artista, album, genero, anio, duracion, tamanio, ruta);

    return musica;
}
        public void agregarMusicaATabla(MUSICA musica) {

        DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();

        Object fila[] = new Object[8];

        fila[0] = musica.getNombre();
        fila[1] = musica.getArtista();
        fila[2] = musica.getAlbum();
        fila[3] = musica.getGenero();
        fila[4] = musica.getAnio();
        fila[5] = musica.getDuracion();
        fila[6] = musica.getTamanioMB();
        fila[7] = musica.getRuta();

        modelo.addRow(fila);
    }
        
        
    public void seleccionarCancionEnTabla(String ruta) {

    for (int i = 0; i < tablaMusica.getRowCount(); i++) {

        String rutaTabla = tablaMusica.getValueAt(i, 7).toString();

        if (rutaTabla.equals(ruta)) {

            tablaMusica.setRowSelectionInterval(i, i);

            tablaMusica.scrollRectToVisible(
                    tablaMusica.getCellRect(i, 0, true)
            );

            return;
        }
    }
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMusica = new javax.swing.JTable();
        BTN_PLAY = new javax.swing.JButton();
        BTN_ATRAS = new javax.swing.JButton();
        BTN_SIGUIENTE = new javax.swing.JButton();
        BTN_DETENER = new javax.swing.JButton();
        BTN_CARGARCARPETA = new javax.swing.JButton();
        BTN_CREAR_PLAY = new javax.swing.JButton();
        BTN_AGREGSAR = new javax.swing.JButton();
        cmbPlaylistsBiblioteca = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        BTN_AGREAGRCOLA = new javax.swing.JButton();
        LBL_PORTADA = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        tablaMusica.setBackground(new java.awt.Color(0, 0, 0));
        tablaMusica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title", "Title", "Title", "Title"
            }
        ));
        jScrollPane1.setViewportView(tablaMusica);
        if (tablaMusica.getColumnModel().getColumnCount() > 0) {
            tablaMusica.getColumnModel().getColumn(2).setResizable(false);
            tablaMusica.getColumnModel().getColumn(3).setResizable(false);
        }

        BTN_PLAY.setText("play");
        BTN_PLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PLAYActionPerformed(evt);
            }
        });

        BTN_ATRAS.setText("ATRAS");
        BTN_ATRAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ATRASActionPerformed(evt);
            }
        });

        BTN_SIGUIENTE.setText("siguiente");
        BTN_SIGUIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SIGUIENTEActionPerformed(evt);
            }
        });

        BTN_DETENER.setText("DETENER");
        BTN_DETENER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DETENERActionPerformed(evt);
            }
        });

        BTN_CARGARCARPETA.setBackground(new java.awt.Color(0, 0, 0));
        BTN_CARGARCARPETA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cargarcarpeta.png"))); // NOI18N
        BTN_CARGARCARPETA.setBorderPainted(false);
        BTN_CARGARCARPETA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CARGARCARPETAActionPerformed(evt);
            }
        });

        BTN_CREAR_PLAY.setBackground(new java.awt.Color(0, 0, 0));
        BTN_CREAR_PLAY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/crearplay.png"))); // NOI18N
        BTN_CREAR_PLAY.setBorderPainted(false);
        BTN_CREAR_PLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CREAR_PLAYActionPerformed(evt);
            }
        });

        BTN_AGREGSAR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_AGREGSAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregaraaaaaa.png"))); // NOI18N
        BTN_AGREGSAR.setBorderPainted(false);
        BTN_AGREGSAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AGREGSARActionPerformed(evt);
            }
        });

        cmbPlaylistsBiblioteca.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Siemens Sans SC Black", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("PLAYLIST:");

        BTN_AGREAGRCOLA.setText("COLA");
        BTN_AGREAGRCOLA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AGREAGRCOLAActionPerformed(evt);
            }
        });

        LBL_PORTADA.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_CARGARCARPETA)
                        .addGap(292, 292, 292)
                        .addComponent(BTN_CREAR_PLAY)
                        .addGap(32, 32, 32)
                        .addComponent(BTN_AGREGSAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbPlaylistsBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1033, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LBL_PORTADA, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(66, 66, 66)
                        .addComponent(BTN_ATRAS)
                        .addGap(45, 45, 45)
                        .addComponent(BTN_PLAY)
                        .addGap(49, 49, 49)
                        .addComponent(BTN_SIGUIENTE)
                        .addGap(78, 78, 78)
                        .addComponent(BTN_DETENER)
                        .addGap(44, 44, 44)
                        .addComponent(BTN_AGREAGRCOLA)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BTN_AGREGSAR)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(cmbPlaylistsBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(BTN_CARGARCARPETA, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BTN_CREAR_PLAY, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBL_PORTADA, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BTN_AGREAGRCOLA)
                                .addComponent(BTN_ATRAS)
                                .addComponent(BTN_PLAY)
                                .addComponent(BTN_SIGUIENTE)
                                .addComponent(BTN_DETENER)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_CARGARCARPETAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CARGARCARPETAActionPerformed
        // TODO add your handling code here:
        javax.swing.JFileChooser buscar = new javax.swing.JFileChooser();
buscar.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

int opcion = buscar.showOpenDialog(this);

if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {

    java.io.File carpeta = buscar.getSelectedFile();

    int respuesta = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Desea limpiar la biblioteca actual antes de cargar la nueva carpeta?",
            "Cargar carpeta",
            javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (respuesta == javax.swing.JOptionPane.YES_OPTION) {

        principal.arbolABB = new ARBOL_ABB();
        principal.arbolAVL = new ARBOL_AVL();
        principal.hashArtista = new TABLA_HASH(10000);
        principal.hashGenero = new TABLA_HASH(2000);

        principal.listaMusica = new LISTA_SIMPLE();
        principal.listaDoble = new LISTA_DOBLE();
        principal.listaCircular = new LISTA_CIRCULAR();

        principal.tiempoCargaABB = 0;
        principal.tiempoCargaAVL = 0;
        principal.totalCancionesCargadas = 0;

        DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();
        modelo.setRowCount(0);

    } else if (respuesta == javax.swing.JOptionPane.NO_OPTION) {

        principal.tiempoCargaABB = 0;
        principal.tiempoCargaAVL = 0;
        principal.totalCancionesCargadas = 0;

    } else {
        return;
    }

    
    cargarMusicaDeCarpeta(carpeta);

    DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();

    modelo.setRowCount(0);

    principal.arbolABB.llenarTablaInorden(principal.arbolABB.getRaiz(), modelo);

    principal.listaDoble = new LISTA_DOBLE();
    principal.arbolABB.llenarListaDobleInorden(
            principal.arbolABB.getRaiz(),
            principal.listaDoble
    );

    double tiempoABBms = principal.tiempoCargaABB / 1000000.0;
    double tiempoAVLms = principal.tiempoCargaAVL / 1000000.0;

    javax.swing.JOptionPane.showMessageDialog(this,
            "Carga finalizada"
            + "\nCanciones nuevas cargadas: " + principal.totalCancionesCargadas
            + "\nTiempo carga ABB: " + tiempoABBms + " ms"
            + "\nTiempo carga AVL: " + tiempoAVLms + " ms"
    );
    }
    }//GEN-LAST:event_BTN_CARGARCARPETAActionPerformed

    private void BTN_PLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PLAYActionPerformed
        // TODO add your handling code here:
         int fila = tablaMusica.getSelectedRow();

    if (fila == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una cancion de la tabla");
        return;
    }

    String rutaSeleccionada = tablaMusica.getValueAt(fila, 7).toString();

    principal.listaDoble.ponerActualPorRuta(rutaSeleccionada);

    MUSICA musica = obtenerMusicaDeTabla(fila);
    principal.historial.apilar(musica);

    if (principal.mp3.getRutaActual().equals("")) {

        principal.mp3.reproducir(rutaSeleccionada);

        PORTADA_MP3 portada = new PORTADA_MP3();
        portada.mostrarPortada(rutaSeleccionada, LBL_PORTADA);

        BTN_PLAY.setText("Pausa");

    } else if (principal.mp3.getRutaActual().equals(rutaSeleccionada)) {

        principal.mp3.playPausa();

        if (principal.mp3.estaSonando()) {
            BTN_PLAY.setText("Pausa");
        } else {
            BTN_PLAY.setText("Play");
        }

    } else {

        principal.mp3.reproducir(rutaSeleccionada);

        PORTADA_MP3 portada = new PORTADA_MP3();
        portada.mostrarPortada(rutaSeleccionada, LBL_PORTADA);

        BTN_PLAY.setText("Pausa");
    }
    }//GEN-LAST:event_BTN_PLAYActionPerformed

    private void BTN_ATRASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ATRASActionPerformed
        // TODO add your handling code here:
       
    MUSICA musica = principal.listaDoble.anterior();

    if (musica == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "No hay cancion anterior");
    } else {
        principal.historial.apilar(musica);
        principal.mp3.reproducir(musica.getRuta());
        BTN_PLAY.setText("Pausa");

        seleccionarCancionEnTabla(musica.getRuta());
    }
    }//GEN-LAST:event_BTN_ATRASActionPerformed

    private void BTN_SIGUIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SIGUIENTEActionPerformed
        // TODO add your handling code here:
        MUSICA musica = principal.listaDoble.siguiente();

    if (musica == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "No hay cancion siguiente");
    } else {
        principal.historial.apilar(musica);
        principal.mp3.reproducir(musica.getRuta());
        BTN_PLAY.setText("Pausa");

        seleccionarCancionEnTabla(musica.getRuta());
    }
    }//GEN-LAST:event_BTN_SIGUIENTEActionPerformed

    private void BTN_DETENERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DETENERActionPerformed
        // TODO add your handling code here:
          principal.mp3.detener();
    }//GEN-LAST:event_BTN_DETENERActionPerformed

    private void BTN_AGREGSARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AGREGSARActionPerformed
        // TODO add your handling code here:
        
   
    int fila = tablaMusica.getSelectedRow();

    if (fila == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una cancion de la tabla");
        return;
    }

    if (cmbPlaylistsBiblioteca.getSelectedItem() == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una playlist");
        return;
    }

    String nombrePlaylist = cmbPlaylistsBiblioteca.getSelectedItem().toString();

    PLAYLIST playlist = principal.VariosPlaylist.buscarPlaylist(nombrePlaylist);

    if (playlist == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "No existe esa playlist");
        return;
    }

    MUSICA musica = obtenerMusicaDeTabla(fila);

    playlist.agregar(musica);

    if (principal.panelPlay != null) {
        principal.panelPlay.actualizarComboPlaylists();
        principal.panelPlay.mostrarPlaylist(nombrePlaylist);
    }

    javax.swing.JOptionPane.showMessageDialog(this,
            "Cancion agregada a la playlist: " + nombrePlaylist);
       
    }//GEN-LAST:event_BTN_AGREGSARActionPerformed

    private void BTN_CREAR_PLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CREAR_PLAYActionPerformed
        // TODO add your handling code here:
        String nombre = javax.swing.JOptionPane.showInputDialog(this, "Ingrese el nombre de la playlist");

    if (nombre == null || nombre.equals("")) {
        return;
    }

    PLAYLIST existe = principal.VariosPlaylist.buscarPlaylist(nombre);

    if (existe != null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Ya existe una playlist con ese nombre");
        return;
    }

    principal.VariosPlaylist.crearPlaylist(nombre);

    cmbPlaylistsBiblioteca.addItem(nombre);

    if (principal.panelPlay != null) {
        principal.panelPlay.actualizarComboPlaylists();
    }

    javax.swing.JOptionPane.showMessageDialog(this, "Playlist creada correctamente");
    }//GEN-LAST:event_BTN_CREAR_PLAYActionPerformed

    private void BTN_AGREAGRCOLAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AGREAGRCOLAActionPerformed
        // TODO add your handling code here:
        
       int fila = tablaMusica.getSelectedRow();

    if (fila == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una cancion para agregar a la cola");
        return;
    }

    MUSICA musica = obtenerMusicaDeTabla(fila);

    principal.cola.arriba(musica);

    if (principal.panelCola != null) {
        principal.panelCola.actualizarTablaCola();
    }

    javax.swing.JOptionPane.showMessageDialog(this, "Cancion agregada a la cola");
    
    }//GEN-LAST:event_BTN_AGREAGRCOLAActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_AGREAGRCOLA;
    private javax.swing.JButton BTN_AGREGSAR;
    private javax.swing.JButton BTN_ATRAS;
    private javax.swing.JButton BTN_CARGARCARPETA;
    private javax.swing.JButton BTN_CREAR_PLAY;
    private javax.swing.JButton BTN_DETENER;
    private javax.swing.JButton BTN_PLAY;
    private javax.swing.JButton BTN_SIGUIENTE;
    private javax.swing.JLabel LBL_PORTADA;
    private javax.swing.JComboBox<String> cmbPlaylistsBiblioteca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMusica;
    // End of variables declaration//GEN-END:variables
}
