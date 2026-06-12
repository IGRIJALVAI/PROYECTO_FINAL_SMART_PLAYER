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
    boolean actualizandoCombo = false;
    javax.swing.Timer timerMusica;
    boolean moviendoSlider = false;
    
    
  
    
    public PANEL_PLAYLIST() {
        initComponents();
    }
    
    public PANEL_PLAYLIST(RESPRODUCTORMP3 principal) {
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
     String columnas[] = {"Nombre", "Artista", "Album", "Genero", "Año", "Duracion", "Tamaño MB", "Ruta"};

    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    tablaPlay.setModel(modelo);

    cargarPlaylist();
    estiloTablaSpotify();
    COMBOXENCRIP.removeAllItems();
    COMBOXENCRIP.addItem("CESAR");
    COMBOXENCRIP.addItem("XOR");
    COMBOXENCRIP.addItem("INVERTIDO");
    COMBOXENCRIP.addItem("BASE64");

    actualizarComboPlaylists();
  
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
     public void actualizarPortada(String ruta) {

    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            PORTADA_MP3 portada = new PORTADA_MP3();
            portada.mostrarPortada(ruta, LBL_PORTADA);

            LBL_PORTADA.revalidate();
            LBL_PORTADA.repaint();
        }
    });
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

    actualizandoCombo = true;

    cmbPlaylists.removeAllItems();

    for (int i = 0; i < principal.VariosPlaylist.contador; i++) {
        cmbPlaylists.addItem(principal.VariosPlaylist.playlists[i].getNombre());
    }

    actualizandoCombo = false;
}
    public void mostrarPlaylist(String nombrePlaylist) {

    PLAYLIST playlistEncontrada = principal.VariosPlaylist.buscarPlaylist(nombrePlaylist);

    if (playlistEncontrada == null) {
        return;
    }

    this.playlist = playlistEncontrada;

    cargarPlaylist();
}
    
    
    
    public void actualizarInfoCancion(String nombre, String artista) {

    String nombreCorto = cortarTexto(nombre, 25);
    String artistaCorto = cortarTexto(artista, 20);

    LBL_CANCION_ACTUAL.setText("Cancion: " + nombreCorto);
    LBL_ARTISTA_ACTUAL.setText("Artista: " + artistaCorto);

    LBL_CANCION_ACTUAL.setToolTipText(nombre);
    LBL_ARTISTA_ACTUAL.setToolTipText(artista);
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

    String nombre = tablaPlay.getValueAt(fila, 0).toString();
    String artista = tablaPlay.getValueAt(fila, 1).toString();
    String album = tablaPlay.getValueAt(fila, 2).toString();
    String genero = tablaPlay.getValueAt(fila, 3).toString();
    int anio = Integer.parseInt(tablaPlay.getValueAt(fila, 4).toString());
    String duracion = tablaPlay.getValueAt(fila, 5).toString();
    double tamanio = Double.parseDouble(tablaPlay.getValueAt(fila, 6).toString());
    String ruta = tablaPlay.getValueAt(fila, 7).toString();

    MUSICA musica = new MUSICA(
            nombre,
            artista,
            album,
            genero,
            anio,
            duracion,
            tamanio,
            ruta
    );

    principal.mp3.reproducir(ruta);

    // Aqui registra artista y genero mas escuchado
    principal.estadisticasReproduccion.registrarReproduccion(musica);

    // Slider
    SLIDER_MUSICA.setValue(0);

    // Portada
    actualizarPortada(ruta);

    // Nombre y artista actual
    actualizarInfoCancion(nombre, artista);

    BTN_PLAY.setText("");

}
public MUSICA buscarCancionEnBiblioteca(String nombre, String artista) {

    Nodo_Simple aux = principal.listaMusica.inicio;

    while (aux != null) {

        if (aux.musica.getNombre().equalsIgnoreCase(nombre)
                && aux.musica.getArtista().equalsIgnoreCase(artista)) {

            return aux.musica;
        }

        aux = aux.siguiente;
    }

    return null;
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
        BTN_GUARDAR = new javax.swing.JButton();
        BTN_CARGAR = new javax.swing.JButton();
        BTN_ENCRIPATR = new javax.swing.JButton();
        BTN_DESEMCRIPTAR = new javax.swing.JButton();
        COMBOXENCRIP = new javax.swing.JComboBox<>();
        LBL_PORTADA = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LBL_CANCION_ACTUAL = new javax.swing.JLabel();
        LBL_ARTISTA_ACTUAL = new javax.swing.JLabel();
        SLIDER_MUSICA = new javax.swing.JSlider();

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

        BTN_ATRAS.setBackground(new java.awt.Color(0, 0, 0));
        BTN_ATRAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anterior.jpg"))); // NOI18N
        BTN_ATRAS.setBorderPainted(false);
        BTN_ATRAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ATRASActionPerformed(evt);
            }
        });

        BTN_PLAY.setBackground(new java.awt.Color(0, 0, 0));
        BTN_PLAY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2.png"))); // NOI18N
        BTN_PLAY.setBorderPainted(false);
        BTN_PLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PLAYActionPerformed(evt);
            }
        });

        BTN_SIGUIENTE.setBackground(new java.awt.Color(0, 0, 0));
        BTN_SIGUIENTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/siguiente+.jpg"))); // NOI18N
        BTN_SIGUIENTE.setBorderPainted(false);
        BTN_SIGUIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SIGUIENTEActionPerformed(evt);
            }
        });

        BTN_CIRUCLAR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_CIRUCLAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circular.jpg"))); // NOI18N
        BTN_CIRUCLAR.setBorderPainted(false);
        BTN_CIRUCLAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CIRUCLARActionPerformed(evt);
            }
        });

        BTN_STOP.setBackground(new java.awt.Color(0, 0, 0));
        BTN_STOP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/stop.jpg"))); // NOI18N
        BTN_STOP.setBorderPainted(false);
        BTN_STOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_STOPActionPerformed(evt);
            }
        });

        BTN_ALETARIO.setBackground(new java.awt.Color(0, 0, 0));
        BTN_ALETARIO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aleatoria.jpg"))); // NOI18N
        BTN_ALETARIO.setBorderPainted(false);
        BTN_ALETARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ALETARIOActionPerformed(evt);
            }
        });

        BTN_ELIMINAR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_ELIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        BTN_ELIMINAR.setBorderPainted(false);
        BTN_ELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ELIMINARActionPerformed(evt);
            }
        });

        cmbPlaylists.setBackground(new java.awt.Color(0, 0, 0));
        cmbPlaylists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPlaylistsActionPerformed(evt);
            }
        });

        BTN_GUARDAR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_GUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        BTN_GUARDAR.setBorderPainted(false);
        BTN_GUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_GUARDARActionPerformed(evt);
            }
        });

        BTN_CARGAR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_CARGAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cargar.png"))); // NOI18N
        BTN_CARGAR.setBorderPainted(false);
        BTN_CARGAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CARGARActionPerformed(evt);
            }
        });

        BTN_ENCRIPATR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_ENCRIPATR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/encriptar.png"))); // NOI18N
        BTN_ENCRIPATR.setBorderPainted(false);
        BTN_ENCRIPATR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ENCRIPATRActionPerformed(evt);
            }
        });

        BTN_DESEMCRIPTAR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_DESEMCRIPTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/desencriptar.png"))); // NOI18N
        BTN_DESEMCRIPTAR.setBorderPainted(false);
        BTN_DESEMCRIPTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DESEMCRIPTARActionPerformed(evt);
            }
        });

        COMBOXENCRIP.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("PLAYLIST:");

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
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(LBL_PORTADA, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LBL_CANCION_ACTUAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LBL_ARTISTA_ACTUAL, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                                .addGap(43, 43, 43)
                                .addComponent(BTN_ATRAS, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BTN_PLAY, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(BTN_SIGUIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SLIDER_MUSICA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTN_CIRUCLAR)
                        .addGap(30, 30, 30)
                        .addComponent(BTN_STOP, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(BTN_ALETARIO)
                        .addGap(32, 32, 32)
                        .addComponent(BTN_ELIMINAR, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(BTN_GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_CARGAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_ENCRIPATR, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(COMBOXENCRIP, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_DESEMCRIPTAR)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_ENCRIPATR, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(COMBOXENCRIP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_CARGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_DESEMCRIPTAR, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(BTN_ELIMINAR)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LBL_PORTADA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(BTN_SIGUIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(BTN_PLAY, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(BTN_ATRAS, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(BTN_CIRUCLAR, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(BTN_STOP, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(BTN_ALETARIO, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(SLIDER_MUSICA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 13, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LBL_CANCION_ACTUAL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LBL_ARTISTA_ACTUAL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
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
         actualizarPortada(rutaSeleccionada);

        if (principal.mp3.estaSonando()) {
            BTN_PLAY.setText("");
        } else {
            BTN_PLAY.setText("");
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
            BTN_PLAY.setText("");
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
            BTN_PLAY.setText("");
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
         BTN_PLAY.setText("");
    }//GEN-LAST:event_BTN_STOPActionPerformed

    private void BTN_GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GUARDARActionPerformed
        // TODO add your handling code here:
        if (cmbPlaylists.getSelectedItem() == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una playlist");
        return;
    }

    String nombrePlaylist = cmbPlaylists.getSelectedItem().toString();

    PLAYLIST playlist = principal.VariosPlaylist.buscarPlaylist(nombrePlaylist);

    if (playlist == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "No se encontro la playlist");
        return;
    }

    javax.swing.JFileChooser guardar = new javax.swing.JFileChooser();
    guardar.setDialogTitle("Guardar playlist");

    guardar.setSelectedFile(new java.io.File(nombrePlaylist + ".txt"));

    int opcion = guardar.showSaveDialog(this);

    if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {

        java.io.File archivo = guardar.getSelectedFile();

        try {
            java.io.FileWriter escritor = new java.io.FileWriter(archivo);

            escritor.write("PLAYLIST: " + playlist.getNombre() + "\n");
            escritor.write("-----------------------------------------\n");

            NODO_PLAY aux = playlist.inicio;

            while (aux != null) {
                escritor.write("Nombre: " + aux.musica.getNombre() + "\n");
                escritor.write("Artista: " + aux.musica.getArtista() + "\n");
                escritor.write("Album: " + aux.musica.getAlbum() + "\n");
                escritor.write("Genero: " + aux.musica.getGenero() + "\n");
                escritor.write("Año: " + aux.musica.getAnio() + "\n");
                escritor.write("Duracion: " + aux.musica.getDuracion() + "\n");
                escritor.write("Tamaño: " + aux.musica.getTamanioMB() + "\n");
                escritor.write("Ruta: " + aux.musica.getRuta() + "\n");
                escritor.write("-----------------------------------------\n");

                aux = aux.siguiente;
            }

            escritor.close();

            javax.swing.JOptionPane.showMessageDialog(this, "Playlist guardada correctamente");

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar la playlist");
            System.out.println(e.getMessage());
        }
    }
        
        
    }//GEN-LAST:event_BTN_GUARDARActionPerformed

    private void BTN_CARGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CARGARActionPerformed
        // TODO add your handling code here:
            javax.swing.JFileChooser abrir = new javax.swing.JFileChooser();
    abrir.setDialogTitle("Cargar playlist");

    int opcion = abrir.showOpenDialog(this);

    if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {

        java.io.File archivo = abrir.getSelectedFile();

        try {
            java.io.BufferedReader lector = new java.io.BufferedReader(
                    new java.io.FileReader(archivo)
            );

            String nombrePlaylist = archivo.getName().replace(".txt", "");

            PLAYLIST nueva = principal.VariosPlaylist.buscarPlaylist(nombrePlaylist);

            if (nueva == null) {
                principal.VariosPlaylist.crearPlaylist(nombrePlaylist);
                nueva = principal.VariosPlaylist.buscarPlaylist(nombrePlaylist);
            }

            String nombre = "";
            String artista = "";
            String album = "";
            String genero = "";
            int anio = 0;
            String duracion = "";
            double tamanio = 0;
            String ruta = "";

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.startsWith("Nombre: ")) {
                    nombre = linea.replace("Nombre: ", "");
                }

                if (linea.startsWith("Artista: ")) {
                    artista = linea.replace("Artista: ", "");
                }

                if (linea.startsWith("Album: ")) {
                    album = linea.replace("Album: ", "");
                }

                if (linea.startsWith("Genero: ")) {
                    genero = linea.replace("Genero: ", "");
                }

                if (linea.startsWith("Año: ")) {
                    try {
                        anio = Integer.parseInt(linea.replace("Año: ", ""));
                    } catch (Exception e) {
                        anio = 0;
                    }
                }

                if (linea.startsWith("Duracion: ")) {
                    duracion = linea.replace("Duracion: ", "");
                }

                if (linea.startsWith("Tamaño: ")) {
                    try {
                        tamanio = Double.parseDouble(linea.replace("Tamaño: ", ""));
                    } catch (Exception e) {
                        tamanio = 0;
                    }
                }

                if (linea.startsWith("Ruta: ")) {
    ruta = linea.replace("Ruta: ", "");

    MUSICA musicaEncontrada = buscarCancionEnBiblioteca(nombre, artista);

    if (musicaEncontrada != null) {

        nueva.agregar(musicaEncontrada);

    } else {

        MUSICA musica = new MUSICA(
                nombre,
                artista,
                album,
                genero,
                anio,
                duracion,
                tamanio,
                ruta
        );

        nueva.agregar(musica);
    }

    nombre = "";
    artista = "";
    album = "";
    genero = "";
    anio = 0;
    duracion = "";
    tamanio = 0;
    ruta = "";
        }
       
            }

            lector.close();

            actualizarComboPlaylists();
            cmbPlaylists.setSelectedItem(nombrePlaylist);
            mostrarPlaylist(nombrePlaylist);

            javax.swing.JOptionPane.showMessageDialog(this, "Playlist cargada correctamente");

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar la playlist");
            System.out.println(e.getMessage());
        }
    }
    }//GEN-LAST:event_BTN_CARGARActionPerformed

    private void BTN_ENCRIPATRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ENCRIPATRActionPerformed
        // TODO add your handling code here:
        if (cmbPlaylists.getSelectedItem() == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una playlist");
        return;
    }

    String nombrePlaylist = cmbPlaylists.getSelectedItem().toString();

    PLAYLIST playlist = principal.VariosPlaylist.buscarPlaylist(nombrePlaylist);

    if (playlist == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "No se encontro la playlist");
        return;
    }

    javax.swing.JFileChooser guardar = new javax.swing.JFileChooser();
    guardar.setDialogTitle("Guardar playlist encriptada");
    guardar.setSelectedFile(new java.io.File(nombrePlaylist + "_encriptada.txt"));

    int opcion = guardar.showSaveDialog(this);

    if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {

        java.io.File archivo = guardar.getSelectedFile();

        try {

            String texto = "";

            texto = texto + "PLAYLIST: " + playlist.getNombre() + "\n";
            texto = texto + "-----------------------------------------\n";

            NODO_PLAY aux = playlist.inicio;

            while (aux != null) {

                texto = texto + "Nombre: " + aux.musica.getNombre() + "\n";
                texto = texto + "Artista: " + aux.musica.getArtista() + "\n";
                texto = texto + "Album: " + aux.musica.getAlbum() + "\n";
                texto = texto + "Genero: " + aux.musica.getGenero() + "\n";
                texto = texto + "Año: " + aux.musica.getAnio() + "\n";
                texto = texto + "Duracion: " + aux.musica.getDuracion() + "\n";
                texto = texto + "Tamaño: " + aux.musica.getTamanioMB() + "\n";
                texto = texto + "Ruta: " + aux.musica.getRuta() + "\n";
                texto = texto + "-----------------------------------------\n";

                aux = aux.siguiente;
            }

            ENCRIPTADOR encriptador = new ENCRIPTADOR();

        String metodo = COMBOXENCRIP.getSelectedItem().toString();

        String textoEncriptado = encriptador.encriptarPorMetodo(texto, metodo);

        java.io.FileWriter escritor = new java.io.FileWriter(archivo);
        escritor.write(textoEncriptado);
        escritor.close();

        javax.swing.JOptionPane.showMessageDialog(this,
                "Playlist encriptada correctamente con metodo: " + metodo);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar la playlist encriptada");
            System.out.println(e.getMessage());
        }
    }
    }//GEN-LAST:event_BTN_ENCRIPATRActionPerformed

    private void BTN_DESEMCRIPTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DESEMCRIPTARActionPerformed
        // TODO add your handling code here:
        javax.swing.JFileChooser abrir = new javax.swing.JFileChooser();
    abrir.setDialogTitle("Cargar playlist encriptada");

    int opcion = abrir.showOpenDialog(this);

    if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {

        java.io.File archivo = abrir.getSelectedFile();

        try {

            java.io.BufferedReader lectorArchivo = new java.io.BufferedReader(
                    new java.io.FileReader(archivo)
            );

            String textoEncriptado = "";
            String lineaArchivo;

            while ((lineaArchivo = lectorArchivo.readLine()) != null) {
                textoEncriptado = textoEncriptado + lineaArchivo + "\n";
            }

            lectorArchivo.close();

            ENCRIPTADOR encriptador = new ENCRIPTADOR();

            String textoNormal = encriptador.desencriptarAutomatico(textoEncriptado);

            if (textoNormal.equals("")) {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo desencriptar el archivo");
                return;
            }

            java.io.BufferedReader lector = new java.io.BufferedReader(
                    new java.io.StringReader(textoNormal)
            );

            String nombrePlaylist = archivo.getName()
                    .replace("_encriptada.txt", "")
                    .replace(".txt", "");

            PLAYLIST nueva = principal.VariosPlaylist.buscarPlaylist(nombrePlaylist);

            if (nueva == null) {
                principal.VariosPlaylist.crearPlaylist(nombrePlaylist);
                nueva = principal.VariosPlaylist.buscarPlaylist(nombrePlaylist);
            }

            String nombre = "";
            String artista = "";
            String album = "";
            String genero = "";
            int anio = 0;
            String duracion = "";
            double tamanio = 0;
            String ruta = "";

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.startsWith("Nombre: ")) {
                    nombre = linea.replace("Nombre: ", "");
                }

                if (linea.startsWith("Artista: ")) {
                    artista = linea.replace("Artista: ", "");
                }

                if (linea.startsWith("Album: ")) {
                    album = linea.replace("Album: ", "");
                }

                if (linea.startsWith("Genero: ")) {
                    genero = linea.replace("Genero: ", "");
                }

                if (linea.startsWith("Año: ")) {
                    try {
                        anio = Integer.parseInt(linea.replace("Año: ", ""));
                    } catch (Exception e) {
                        anio = 0;
                    }
                }

                if (linea.startsWith("Duracion: ")) {
                    duracion = linea.replace("Duracion: ", "");
                }

                if (linea.startsWith("Tamaño: ")) {
                    try {
                        tamanio = Double.parseDouble(linea.replace("Tamaño: ", ""));
                    } catch (Exception e) {
                        tamanio = 0;
                    }
                }

                if (linea.startsWith("Ruta: ")) {
                    ruta = linea.replace("Ruta: ", "");

                    MUSICA musicaEncontrada = buscarCancionEnBiblioteca(nombre, artista);

                    if (musicaEncontrada != null) {
                        nueva.agregar(musicaEncontrada);
                    } else {
                        MUSICA musica = new MUSICA(
                                nombre,
                                artista,
                                album,
                                genero,
                                anio,
                                duracion,
                                tamanio,
                                ruta
                        );

                        nueva.agregar(musica);
                    }

                    nombre = "";
                    artista = "";
                    album = "";
                    genero = "";
                    anio = 0;
                    duracion = "";
                    tamanio = 0;
                    ruta = "";
                }
            }

            lector.close();

            actualizarComboPlaylists();
            cmbPlaylists.setSelectedItem(nombrePlaylist);
            mostrarPlaylist(nombrePlaylist);

            javax.swing.JOptionPane.showMessageDialog(this, "Playlist encriptada cargada correctamente");

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar la playlist encriptada");
            System.out.println(e.getMessage());
        }
    }
    }//GEN-LAST:event_BTN_DESEMCRIPTARActionPerformed

    private void cmbPlaylistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPlaylistsActionPerformed
        // TODO add your handling code here:
         if (actualizandoCombo == true) {
        return;
    }

    if (cmbPlaylists.getSelectedItem() != null) {
        String nombrePlaylist = cmbPlaylists.getSelectedItem().toString();
        mostrarPlaylist(nombrePlaylist);
    }
    }//GEN-LAST:event_cmbPlaylistsActionPerformed

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
    private javax.swing.JButton BTN_ALETARIO;
    private javax.swing.JButton BTN_ATRAS;
    private javax.swing.JButton BTN_CARGAR;
    private javax.swing.JButton BTN_CIRUCLAR;
    private javax.swing.JButton BTN_DESEMCRIPTAR;
    private javax.swing.JButton BTN_ELIMINAR;
    private javax.swing.JButton BTN_ENCRIPATR;
    private javax.swing.JButton BTN_GUARDAR;
    private javax.swing.JButton BTN_PLAY;
    private javax.swing.JButton BTN_SIGUIENTE;
    private javax.swing.JButton BTN_STOP;
    private javax.swing.JComboBox<String> COMBOXENCRIP;
    private javax.swing.JLabel LBL_ARTISTA_ACTUAL;
    private javax.swing.JLabel LBL_CANCION_ACTUAL;
    private javax.swing.JLabel LBL_PORTADA;
    private javax.swing.JSlider SLIDER_MUSICA;
    private javax.swing.JComboBox<String> cmbPlaylists;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPlay;
    // End of variables declaration//GEN-END:variables
}
