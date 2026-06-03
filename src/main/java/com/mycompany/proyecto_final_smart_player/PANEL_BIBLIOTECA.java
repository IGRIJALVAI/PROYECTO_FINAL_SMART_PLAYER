
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

                    principal.listaMusica.agregar(musica);
                    principal.listaDoble.agregar(musica);
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        BTN_CARGARCARPETA = new javax.swing.JButton();
        BTN_CREAR_PLAY = new javax.swing.JButton();
        BTN_AGREGSAR = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));

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

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        BTN_CARGARCARPETA.setText("CARGAR");
        BTN_CARGARCARPETA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CARGARCARPETAActionPerformed(evt);
            }
        });

        BTN_CREAR_PLAY.setText("CERAR PLAY");
        BTN_CREAR_PLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CREAR_PLAYActionPerformed(evt);
            }
        });

        BTN_AGREGSAR.setText("jButton5");
        BTN_AGREGSAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AGREGSARActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jButton2)
                        .addGap(46, 46, 46)
                        .addComponent(BTN_PLAY)
                        .addGap(46, 46, 46)
                        .addComponent(jButton3)
                        .addGap(152, 152, 152)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN_CARGARCARPETA)
                            .addComponent(BTN_CREAR_PLAY)
                            .addComponent(BTN_AGREGSAR))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_CARGARCARPETA)
                        .addGap(46, 46, 46)
                        .addComponent(BTN_CREAR_PLAY)
                        .addGap(27, 27, 27)
                        .addComponent(BTN_AGREGSAR)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_PLAY)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(94, Short.MAX_VALUE))
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

            principal.listaMusica = new LISTA_SIMPLE();
            principal.listaDoble = new LISTA_DOBLE();
            principal.listaCircular = new LISTA_CIRCULAR();

            principal.tiempoCargaABB = 0;
            principal.tiempoCargaAVL = 0;
            principal.totalCancionesCargadas = 0;

            DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();
            modelo.setRowCount(0);

        } else {

            principal.tiempoCargaABB = 0;
            principal.tiempoCargaAVL = 0;
            principal.totalCancionesCargadas = 0;
        }

        cargarMusicaDeCarpeta(carpeta);

        DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();

        modelo.setRowCount(0);

        principal.arbolABB.llenarTablaInorden(principal.arbolABB.getRaiz(), modelo);

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

        if (principal.mp3.getRutaActual().equals("")) {

            principal.mp3.reproducir(rutaSeleccionada);
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
            BTN_PLAY.setText("Pausa");

        }
    }//GEN-LAST:event_BTN_PLAYActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         MUSICA musica =  principal.listaDoble.anterior();

        if (musica != null) {
             principal.mp3.reproducir(musica.getRuta());
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay canción anterior");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         MUSICA musica = principal.listaDoble.siguiente();

        if (musica != null) {
            principal.mp3.reproducir(musica.getRuta());
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay canción siguiente");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
          principal.mp3.detener();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void BTN_AGREGSARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AGREGSARActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_BTN_AGREGSARActionPerformed

    private void BTN_CREAR_PLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CREAR_PLAYActionPerformed
        // TODO add your handling code here:
         String nombre = javax.swing.JOptionPane.showInputDialog(this, "Ingrese el nombre de la playlist");

        if (nombre != null && !nombre.equals("")) {

             principal.VariosPlaylist.crearPlaylist(nombre);

            

            javax.swing.JOptionPane.showMessageDialog(this, "Playlist creada correctamente");
        }
    }//GEN-LAST:event_BTN_CREAR_PLAYActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_AGREGSAR;
    private javax.swing.JButton BTN_CARGARCARPETA;
    private javax.swing.JButton BTN_CREAR_PLAY;
    private javax.swing.JButton BTN_PLAY;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMusica;
    // End of variables declaration//GEN-END:variables
}
