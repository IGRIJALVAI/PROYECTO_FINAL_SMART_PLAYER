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
public class PANEL_COLA extends javax.swing.JPanel {

    /**
     * Creates new form PANEL_COLA
     */
    RESPRODUCTORMP3 principal;
    PANEL_COLA panelCola;
    javax.swing.Timer timerMusica;
    boolean moviendoSlider = false;

        public PANEL_COLA() {
               initComponents();
               
           }

    public PANEL_COLA(RESPRODUCTORMP3 principal) {
         initComponents();
    this.principal = principal;
    
    SLIDER_MUSICA.setMinimum(0);
SLIDER_MUSICA.setValue(0);

timerMusica = new javax.swing.Timer(500, new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {

        if (principal.mp3.reproductor != null && moviendoSlider == false) {

            int duracion = (int) principal.mp3.obtenerDuracion();
            int actual = (int) principal.mp3.obtenerTiempoActual();

            if (duracion > 0) {
                SLIDER_MUSICA.setMaximum(duracion);
                SLIDER_MUSICA.setValue(actual);

            }
        }
    }
});

timerMusica.start();

    String columnas[] = {"Nombre", "Artista", "Album", "Genero", "Año", "Duracion", "Tamaño", "Ruta"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
    TABLACOLA.setModel(modelo);

    actualizarTablaCola();
             estiloTablaSpotify();
    }
    
    
    
    public String formatoTiempo(int segundosTotales) {

    int minutos = segundosTotales / 60;
    int segundos = segundosTotales % 60;

    String textoSegundos = "";

    if (segundos < 10) {
        textoSegundos = "0" + segundos;
    } else {
        textoSegundos = "" + segundos;
    }

    return minutos + ":" + textoSegundos;
}
    
    
    public void actualizarTablaCola() {

    DefaultTableModel modelo = (DefaultTableModel) TABLACOLA.getModel();
    modelo.setRowCount(0);

    nodo_coola aux = principal.cola.inicio;

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

    TABLACOLA.setBackground(new Color(0, 0, 0));
    TABLACOLA.setForeground(Color.WHITE);
    TABLACOLA.setGridColor(new Color(35, 35, 35));

    
    TABLACOLA.setSelectionBackground(new Color(180, 0, 0)); // cuadno se oprime se pone rojo

    TABLACOLA.setRowHeight(35);
    TABLACOLA.setShowGrid(true);
    TABLACOLA.setOpaque(true);
    TABLACOLA.setFillsViewportHeight(true);

    jScrollPane1.getViewport().setBackground(new Color(0, 0, 0));
    jScrollPane1.setBackground(new Color(0, 0, 0));
    jScrollPane1.setBorder(null);

    JTableHeader header = TABLACOLA.getTableHeader();
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

    for (int i = 0; i < TABLACOLA.getColumnCount(); i++) {
        TABLACOLA.getColumnModel().getColumn(i).setCellRenderer(render);
    }
}
    public String cortarTexto(String texto, int limite) {

    if (texto == null) {
        return "Desconocido";
    }

    if (texto.length() > limite) {
        return texto.substring(0, limite) + "...";
    }

    return texto;
}

public void actualizarInfoCancion(String nombre, String artista) {

    String nombreCorto = cortarTexto(nombre, 25);
    String artistaCorto = cortarTexto(artista, 20);

    LBL_CANCION_ACTUAL.setText("Cancion: " + nombreCorto);
    LBL_ARTISTA_ACTUAL.setText("Artista: " + artistaCorto);

    LBL_CANCION_ACTUAL.setToolTipText(nombre);
    LBL_ARTISTA_ACTUAL.setToolTipText(artista);
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
        TABLACOLA = new javax.swing.JTable();
        BTN_LIMPAIRT = new javax.swing.JButton();
        BTN_OPLAY = new javax.swing.JButton();
        BTN_LIMINAR = new javax.swing.JButton();
        LBL_PORTADA = new javax.swing.JLabel();
        LBL_CANCION_ACTUAL = new javax.swing.JLabel();
        LBL_ARTISTA_ACTUAL = new javax.swing.JLabel();
        SLIDER_MUSICA = new javax.swing.JSlider();

        setBackground(new java.awt.Color(0, 0, 0));

        TABLACOLA.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TABLACOLA);

        BTN_LIMPAIRT.setBackground(new java.awt.Color(0, 0, 0));
        BTN_LIMPAIRT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
        BTN_LIMPAIRT.setBorderPainted(false);
        BTN_LIMPAIRT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_LIMPAIRTActionPerformed(evt);
            }
        });

        BTN_OPLAY.setBackground(new java.awt.Color(0, 0, 0));
        BTN_OPLAY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2.png"))); // NOI18N
        BTN_OPLAY.setBorderPainted(false);
        BTN_OPLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_OPLAYActionPerformed(evt);
            }
        });

        BTN_LIMINAR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_LIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        BTN_LIMINAR.setBorderPainted(false);
        BTN_LIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_LIMINARActionPerformed(evt);
            }
        });

        LBL_PORTADA.setBackground(new java.awt.Color(0, 0, 0));

        SLIDER_MUSICA.setBackground(new java.awt.Color(0, 0, 0));
        SLIDER_MUSICA.setForeground(new java.awt.Color(153, 0, 0));
        SLIDER_MUSICA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SLIDER_MUSICAMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SLIDER_MUSICAMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LBL_PORTADA, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LBL_CANCION_ACTUAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LBL_ARTISTA_ACTUAL, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(BTN_OPLAY, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118)
                                .addComponent(BTN_LIMINAR)
                                .addGap(18, 18, 18)
                                .addComponent(BTN_LIMPAIRT))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(SLIDER_MUSICA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBL_PORTADA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BTN_LIMINAR)
                                            .addComponent(BTN_LIMPAIRT)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(LBL_CANCION_ACTUAL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LBL_ARTISTA_ACTUAL, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(BTN_OPLAY, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SLIDER_MUSICA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_OPLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_OPLAYActionPerformed
        // TODO add your handling code here:
        
       
       MUSICA musica = principal.cola.abajo();
       

    if (musica == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "La cola esta vacia");
        return;
    }

    principal.mp3.reproducir(musica.getRuta());
    principal.estadisticasReproduccion.registrarReproduccion(musica);//PORTADA Y SLIDER Y SUS NLKNRES
    SLIDER_MUSICA.setValue(0);
   PORTADA_MP3 portada = new PORTADA_MP3();
   portada.mostrarPortada(musica.getRuta(), LBL_PORTADA);
     actualizarInfoCancion(musica.getNombre(), musica.getArtista());

    principal.historial.apilar(musica);

    actualizarTablaCola();
    }//GEN-LAST:event_BTN_OPLAYActionPerformed

    private void BTN_LIMPAIRTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_LIMPAIRTActionPerformed
        // TODO add your handling code here:
        principal.cola.limpiar();
     actualizarTablaCola();

    javax.swing.JOptionPane.showMessageDialog(this, "Cola limpiada correctamente");
    }//GEN-LAST:event_BTN_LIMPAIRTActionPerformed

    private void BTN_LIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_LIMINARActionPerformed
        // TODO add your handling code here:
        int fila = TABLACOLA.getSelectedRow();

    if (fila == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una cancion de la cola");
        return;
    }

    String ruta = TABLACOLA.getValueAt(fila, 7).toString();

    principal.cola.eliminarPorRuta(ruta);

    actualizarTablaCola();

    javax.swing.JOptionPane.showMessageDialog(this, "Cancion eliminada de la cola");
    }//GEN-LAST:event_BTN_LIMINARActionPerformed

    private void SLIDER_MUSICAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SLIDER_MUSICAMousePressed
        // TODO add your handling code here:
        
         moviendoSlider = true;
    }//GEN-LAST:event_SLIDER_MUSICAMousePressed

    private void SLIDER_MUSICAMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SLIDER_MUSICAMouseReleased
        // TODO add your handling code here:
        
        int segundos = SLIDER_MUSICA.getValue();

        principal.mp3.irA(segundos);

        moviendoSlider = false;
    }//GEN-LAST:event_SLIDER_MUSICAMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_LIMINAR;
    private javax.swing.JButton BTN_LIMPAIRT;
    private javax.swing.JButton BTN_OPLAY;
    private javax.swing.JLabel LBL_ARTISTA_ACTUAL;
    private javax.swing.JLabel LBL_CANCION_ACTUAL;
    private javax.swing.JLabel LBL_PORTADA;
    private javax.swing.JSlider SLIDER_MUSICA;
    private javax.swing.JTable TABLACOLA;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
