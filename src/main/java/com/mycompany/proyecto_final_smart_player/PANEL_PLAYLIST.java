/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
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
public class PANEL_PLAYLIST extends javax.swing.JPanel {

    /**
     * Creates new form PANEL_PLAYLIST
     */
    RESPRODUCTORMP3 principal;
    
    MP3 mp3 = new MP3();
    PLAYLIST playlist;
    boolean circularActivo = false;
    int indiceActual = -1;
    
    
  
    
    public PANEL_PLAYLIST() {
        initComponents();
    }
    
    public PANEL_PLAYLIST(RESPRODUCTORMP3 principal) {
    initComponents();

    this.principal = principal;
     String columnas[] = {"Nombre", "Artista", "Album", "Genero", "Año", "Duracion", "Tamaño MB", "Ruta"};

    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    tablaPlay.setModel(modelo);

    cargarPlaylist();
    estiloTablaSpotify();
  
    }
    public void recibirPlaylist(PLAYLIST playlist) {
    this.playlist = playlist;
    cargarPlaylist();
    }
    
    
     public void cargarPlaylist() {

    DefaultTableModel modelo = (DefaultTableModel) tablaPlay.getModel();

    modelo.setRowCount(0);

    if (playlist == null) {
        return;
    }

    NODO_PLAY aux = playlist.inicio;

    while (aux != null) {

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

        aux = aux.siguiente;
    }
}
     
    private void estiloTablaSpotify() {

    tablaPlay.setBackground(new Color(0, 0, 0));
    tablaPlay.setForeground(Color.WHITE);
    tablaPlay.setGridColor(new Color(35, 35, 35));

    
    tablaPlay.setSelectionBackground(new Color(180, 0, 0)); // cuadno se oprime se pone rojo

    tablaPlay.setRowHeight(35);
    tablaPlay.setShowGrid(true);
    tablaPlay.setOpaque(true);
    tablaPlay.setFillsViewportHeight(true);

    jScrollPane1.getViewport().setBackground(new Color(0, 0, 0));
    jScrollPane1.setBackground(new Color(0, 0, 0));
    jScrollPane1.setBorder(null);

    JTableHeader header = tablaPlay.getTableHeader();
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
     for (int i = 0; i < tablaPlay.getColumnCount(); i++) {
        tablaPlay.getColumnModel().getColumn(i).setCellRenderer(render);
    }
}
    
    public void actualizarComboPlaylists() {

    cmbPlaylists.removeAllItems();

    for (int i = 0; i < principal.VariosPlaylist.contador; i++) {
        cmbPlaylists.addItem(principal.VariosPlaylist.playlists[i].getNombre());
    }
}
    public void mostrarPlaylist(String nombrePlaylist) {

    PLAYLIST playlistEncontrada = principal.VariosPlaylist.buscarPlaylist(nombrePlaylist);

    if (playlistEncontrada == null) {
        return;
    }

    this.playlist = playlistEncontrada;

    cmbPlaylists.setSelectedItem(nombrePlaylist);

    cargarPlaylist();
}
    
    public void reproducirFila(int fila) {

    int totalFilas = tablaPlay.getRowCount();

    if (totalFilas == 0) {
        javax.swing.JOptionPane.showMessageDialog(this, "La playlist esta vacia");
        return;
    }

    if (fila < 0 || fila >= totalFilas) {
        return;
    }

    indiceActual = fila;

    tablaPlay.setRowSelectionInterval(fila, fila);
    tablaPlay.scrollRectToVisible(tablaPlay.getCellRect(fila, 0, true));

    String ruta = tablaPlay.getValueAt(fila, 7).toString();

    principal.mp3.reproducir(ruta);

    BTN_PLAY.setText("Pausa");
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
        tablaPlay = new javax.swing.JTable();
        BTN_ATRAS = new javax.swing.JButton();
        BTN_PLAY = new javax.swing.JButton();
        BTN_SIGUIENTE = new javax.swing.JButton();
        BTN_CIRUCLAR = new javax.swing.JButton();
        BTN_STOP = new javax.swing.JButton();
        BTN_ALETARIO = new javax.swing.JButton();
        BTN_ELIMINAR = new javax.swing.JButton();
        cmbPlaylists = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(0, 0, 0));

        tablaPlay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaPlay);

        BTN_ATRAS.setText("ATRAS");
        BTN_ATRAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ATRASActionPerformed(evt);
            }
        });

        BTN_PLAY.setText("PLAY");
        BTN_PLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PLAYActionPerformed(evt);
            }
        });

        BTN_SIGUIENTE.setText("SIGUIENTE");
        BTN_SIGUIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SIGUIENTEActionPerformed(evt);
            }
        });

        BTN_CIRUCLAR.setText("CIRCULAR");
        BTN_CIRUCLAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CIRUCLARActionPerformed(evt);
            }
        });

        BTN_STOP.setText("STOP");
        BTN_STOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_STOPActionPerformed(evt);
            }
        });

        BTN_ALETARIO.setText("ALEATRORIO");
        BTN_ALETARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ALETARIOActionPerformed(evt);
            }
        });

        BTN_ELIMINAR.setText("ELIMINAR");
        BTN_ELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ELIMINARActionPerformed(evt);
            }
        });

        cmbPlaylists.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cmbPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(BTN_ATRAS)
                        .addGap(31, 31, 31)
                        .addComponent(BTN_PLAY)
                        .addGap(37, 37, 37)
                        .addComponent(BTN_SIGUIENTE)
                        .addGap(63, 63, 63)
                        .addComponent(BTN_CIRUCLAR)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_STOP)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_ALETARIO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTN_ELIMINAR))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 987, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(cmbPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_ATRAS)
                    .addComponent(BTN_PLAY)
                    .addComponent(BTN_SIGUIENTE)
                    .addComponent(BTN_CIRUCLAR)
                    .addComponent(BTN_STOP)
                    .addComponent(BTN_ALETARIO)
                    .addComponent(BTN_ELIMINAR))
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_PLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PLAYActionPerformed
        // TODO add your handling code here:
              int fila = tablaPlay.getSelectedRow();

    if (fila == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una cancion de la playlist");
        return;
    }

    String rutaSeleccionada = tablaPlay.getValueAt(fila, 7).toString();

    if (principal.mp3.getRutaActual().equals("")) {

        reproducirFila(fila);

    } else if (principal.mp3.getRutaActual().equals(rutaSeleccionada)) {

        principal.mp3.playPausa();

        if (principal.mp3.estaSonando()) {
            BTN_PLAY.setText("Pausa");
        } else {
            BTN_PLAY.setText("Play");
        }

    } else {

        reproducirFila(fila);
    }
    }//GEN-LAST:event_BTN_PLAYActionPerformed

    private void BTN_SIGUIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SIGUIENTEActionPerformed
        // TODO add your handling code here:
         int totalFilas = tablaPlay.getRowCount();

    if (totalFilas == 0) {
        javax.swing.JOptionPane.showMessageDialog(this, "La playlist esta vacia");
        return;
    }

    if (indiceActual == -1) {
        indiceActual = tablaPlay.getSelectedRow();
    }

    if (indiceActual == -1) {
        indiceActual = 0;
    } else {
        indiceActual++;
    }

    if (indiceActual >= totalFilas) {

        if (circularActivo == true) {
            indiceActual = 0;
        } else {
            indiceActual = totalFilas - 1;
            principal.mp3.detener();
            BTN_PLAY.setText("Play");
            javax.swing.JOptionPane.showMessageDialog(this, "Fin de la playlist");
            return;
        }
    }

    reproducirFila(indiceActual);

        
    }//GEN-LAST:event_BTN_SIGUIENTEActionPerformed

    private void BTN_ATRASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ATRASActionPerformed
        // TODO add your handling code here:
        int totalFilas = tablaPlay.getRowCount();

    if (totalFilas == 0) {
        javax.swing.JOptionPane.showMessageDialog(this, "La playlist esta vacia");
        return;
    }

    if (indiceActual == -1) {
        indiceActual = tablaPlay.getSelectedRow();
    }

    if (indiceActual == -1) {
        indiceActual = 0;
    } else {
        indiceActual--;
    }

    if (indiceActual < 0) {

        if (circularActivo == true) {
            indiceActual = totalFilas - 1;
        } else {
            indiceActual = 0;
            principal.mp3.detener();
            BTN_PLAY.setText("Play");
            javax.swing.JOptionPane.showMessageDialog(this, "Inicio de la playlist");
            return;
        }
    }

    reproducirFila(indiceActual);
    }//GEN-LAST:event_BTN_ATRASActionPerformed

    private void BTN_CIRUCLARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CIRUCLARActionPerformed
        // TODO add your handling code here:
          if (circularActivo == false) {
                    circularActivo = true;
                    BTN_CIRUCLAR.setText("Circular ON");
                    javax.swing.JOptionPane.showMessageDialog(this, "Modo circular activado");
                } else {
                    circularActivo = false;
                    BTN_CIRUCLAR.setText("Circular OFF");
                    javax.swing.JOptionPane.showMessageDialog(this, "Modo circular desactivado");
                }
    }//GEN-LAST:event_BTN_CIRUCLARActionPerformed

    private void BTN_ALETARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ALETARIOActionPerformed
        // TODO add your handling code here:
           int totalFilas = tablaPlay.getRowCount();

    if (totalFilas == 0) {
        javax.swing.JOptionPane.showMessageDialog(this, "La playlist esta vacia");
        return;
    }

    int fila = (int) (Math.random() * totalFilas);

    reproducirFila(fila);
    }//GEN-LAST:event_BTN_ALETARIOActionPerformed

    private void BTN_ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ELIMINARActionPerformed
        // TODO add your handling code here:
         int fila = tablaPlay.getSelectedRow();

    if (fila == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una canción para eliminar");
        return;
    }

    String nombreCancion = tablaPlay.getValueAt(fila, 0).toString();

    int respuesta = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro que desea eliminar esta canción de la playlist?\n" + nombreCancion,
            "Confirmar eliminación",
            javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (respuesta == javax.swing.JOptionPane.YES_OPTION) {

        playlist.eliminar(nombreCancion);

        cargarPlaylist();

        javax.swing.JOptionPane.showMessageDialog(this, "Canción eliminada de la playlist");
    }
    }//GEN-LAST:event_BTN_ELIMINARActionPerformed

    private void BTN_STOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_STOPActionPerformed
        // TODO add your handling code here:
         principal.mp3.detener();
         BTN_PLAY.setText("Play");
    }//GEN-LAST:event_BTN_STOPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_ALETARIO;
    private javax.swing.JButton BTN_ATRAS;
    private javax.swing.JButton BTN_CIRUCLAR;
    private javax.swing.JButton BTN_ELIMINAR;
    private javax.swing.JButton BTN_PLAY;
    private javax.swing.JButton BTN_SIGUIENTE;
    private javax.swing.JButton BTN_STOP;
    private javax.swing.JComboBox<String> cmbPlaylists;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPlay;
    // End of variables declaration//GEN-END:variables
}
