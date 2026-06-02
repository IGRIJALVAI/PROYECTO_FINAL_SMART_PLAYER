
package com.mycompany.proyecto_final_smart_player;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author grija
 */
public class RESPRODUCTORMP3 extends javax.swing.JFrame {
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RESPRODUCTORMP3.class.getName());

    /**
     * Creates new form RESPRODUCTORMP3
     */
     ARBOL_ABB arbolABB = new ARBOL_ABB();
     ARBOL_AVL arbolAVL = new ARBOL_AVL();
     MP3 mp3 = new MP3();
     String rutaCancion = "";
     LISTA_SIMPLE listaMusica = new LISTA_SIMPLE();
     LISTA_DOBLE listaDoble = new LISTA_DOBLE();
     PILA historial = new PILA();
     COLA cola = new COLA();
     LISTA_CIRCULAR listaCircular = new LISTA_CIRCULAR();
     VARIAS_PLAYS VariosPlaylist = new VARIAS_PLAYS();
     long tiempoCargaABB = 0;
     long tiempoCargaAVL = 0;
     int totalCancionesCargadas = 0;
     
     
    
    public RESPRODUCTORMP3() {
        initComponents();
        
        getContentPane().setBackground(new Color(18, 18, 18));
        
        
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
                    arbolABB.AgregarNodo(musica);
                    long finABB = System.nanoTime();

                    tiempoCargaABB = tiempoCargaABB + (finABB - inicioABB);

                    long inicioAVL = System.nanoTime();
                    arbolAVL.AgregarNodo(musica);
                    long finAVL = System.nanoTime();

                    tiempoCargaAVL = tiempoCargaAVL + (finAVL - inicioAVL);

                    listaMusica.agregar(musica);
                    listaDoble.agregar(musica);
                    listaCircular.agregar(musica);

                    totalCancionesCargadas++;
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

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        BTON_STOP = new javax.swing.JButton();
        BTN_SIGUIENTE = new javax.swing.JButton();
        BTN_ANTERIOR = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jToggleButton1 = new javax.swing.JToggleButton();
        BTN_CIRULAR = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        BTN_PLAY_MUSICA = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        BTN_CREARPLAY = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        BTN_AGREGAR_CAN_PLAY = new javax.swing.JButton();
        VER_CAN_PLAY = new javax.swing.JButton();
        BTN_ELIMINAR_PLAY = new javax.swing.JButton();
        COMBOX = new javax.swing.JComboBox<>();
        btn_verCOLA = new javax.swing.JButton();
        BNT_REPROCOLA = new javax.swing.JButton();
        BTN_ABRIPLAY = new javax.swing.JButton();
        BTN_COLA = new javax.swing.JButton();
        BTN_CARGARCAPETA = new javax.swing.JButton();
        BTN_HISTORIAL = new javax.swing.JButton();
        BTN_BUSCAR_MUSICA = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMusica = new javax.swing.JTable();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        BTON_STOP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/STOPPP.png"))); // NOI18N
        BTON_STOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTON_STOPActionPerformed(evt);
            }
        });

        BTN_SIGUIENTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SIGUIENTEEEE.png"))); // NOI18N
        BTN_SIGUIENTE.setBorder(null);
        BTN_SIGUIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SIGUIENTEActionPerformed(evt);
            }
        });

        BTN_ANTERIOR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ANTERIORRR.png"))); // NOI18N
        BTN_ANTERIOR.setBorder(null);
        BTN_ANTERIOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ANTERIORActionPerformed(evt);
            }
        });

        jToggleButton1.setText("jToggleButton1");

        BTN_CIRULAR.setBackground(new java.awt.Color(0, 0, 0));
        BTN_CIRULAR.setText("CIRCULAR");
        BTN_CIRULAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CIRULARActionPerformed(evt);
            }
        });

        jToggleButton3.setText("jToggleButton1");

        BTN_PLAY_MUSICA.setBackground(new java.awt.Color(153, 153, 153));
        BTN_PLAY_MUSICA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/PLLAY.png"))); // NOI18N
        BTN_PLAY_MUSICA.setBorder(null);
        BTN_PLAY_MUSICA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PLAY_MUSICAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(BTN_ANTERIOR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTN_PLAY_MUSICA, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTN_SIGUIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_CIRULAR, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(BTON_STOP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTON_STOP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(BTN_PLAY_MUSICA, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BTN_CIRULAR, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BTN_ANTERIOR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BTN_SIGUIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        BTN_CREARPLAY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/creaar.png"))); // NOI18N
        BTN_CREARPLAY.setBorder(null);
        BTN_CREARPLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CREARPLAYActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(180, 0, 0));
        jLabel2.setText("PLAYLIST");

        BTN_AGREGAR_CAN_PLAY.setBackground(new java.awt.Color(0, 0, 0));
        BTN_AGREGAR_CAN_PLAY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar.png"))); // NOI18N
        BTN_AGREGAR_CAN_PLAY.setBorder(null);
        BTN_AGREGAR_CAN_PLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AGREGAR_CAN_PLAYActionPerformed(evt);
            }
        });

        VER_CAN_PLAY.setText("VER");
        VER_CAN_PLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VER_CAN_PLAYActionPerformed(evt);
            }
        });

        BTN_ELIMINAR_PLAY.setText("ELIMINAR");
        BTN_ELIMINAR_PLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ELIMINAR_PLAYActionPerformed(evt);
            }
        });

        COMBOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBOXActionPerformed(evt);
            }
        });

        btn_verCOLA.setText("VER COLA");
        btn_verCOLA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verCOLAActionPerformed(evt);
            }
        });

        BNT_REPROCOLA.setText("REPROCOLA");
        BNT_REPROCOLA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNT_REPROCOLAActionPerformed(evt);
            }
        });

        BTN_ABRIPLAY.setText("ABRIR");
        BTN_ABRIPLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ABRIPLAYActionPerformed(evt);
            }
        });

        BTN_COLA.setText("AÑADIR A COLA");
        BTN_COLA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_COLAActionPerformed(evt);
            }
        });

        BTN_CARGARCAPETA.setBackground(new java.awt.Color(153, 153, 153));
        BTN_CARGARCAPETA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/carpeta (5).png"))); // NOI18N
        BTN_CARGARCAPETA.setBorderPainted(false);
        BTN_CARGARCAPETA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_CARGARCAPETA.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        BTN_CARGARCAPETA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CARGARCAPETAActionPerformed(evt);
            }
        });

        BTN_HISTORIAL.setBackground(new java.awt.Color(0, 0, 0));
        BTN_HISTORIAL.setForeground(new java.awt.Color(255, 255, 255));
        BTN_HISTORIAL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/historias (5).png"))); // NOI18N
        BTN_HISTORIAL.setBorderPainted(false);
        BTN_HISTORIAL.setContentAreaFilled(false);
        BTN_HISTORIAL.setFocusPainted(false);
        BTN_HISTORIAL.setOpaque(true);
        BTN_HISTORIAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_HISTORIALActionPerformed(evt);
            }
        });

        BTN_BUSCAR_MUSICA.setBackground(new java.awt.Color(0, 0, 0));
        BTN_BUSCAR_MUSICA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buiscar (1).png"))); // NOI18N
        BTN_BUSCAR_MUSICA.setBorder(null);
        BTN_BUSCAR_MUSICA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BUSCAR_MUSICAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BTN_COLA)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BTN_ABRIPLAY)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(VER_CAN_PLAY)
                                    .addComponent(COMBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(81, 81, 81))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN_CARGARCAPETA, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btn_verCOLA)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN_CREARPLAY, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BTN_HISTORIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTN_BUSCAR_MUSICA, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(BTN_AGREGAR_CAN_PLAY, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_ELIMINAR_PLAY))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(BNT_REPROCOLA))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(BTN_CARGARCAPETA, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_HISTORIAL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_BUSCAR_MUSICA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(BTN_CREARPLAY, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(BTN_AGREGAR_CAN_PLAY, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_ELIMINAR_PLAY)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_ABRIPLAY))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(VER_CAN_PLAY)
                        .addGap(37, 37, 37)
                        .addComponent(COMBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(148, 148, 148)
                .addComponent(BTN_COLA)
                .addGap(18, 18, 18)
                .addComponent(btn_verCOLA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BNT_REPROCOLA)
                .addContainerGap())
        );

        tablaMusica.setBackground(new java.awt.Color(0, 0, 0));
        tablaMusica.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaMusica);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(293, 293, 293))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_BUSCAR_MUSICAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BUSCAR_MUSICAActionPerformed
        // TODO add your handling code here:
        
        
            javax.swing.JFileChooser buscar = new javax.swing.JFileChooser();
            int opcion = buscar.showOpenDialog(this);

            if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File archivo = buscar.getSelectedFile();

                rutaCancion = archivo.getAbsolutePath();

                javax.swing.JOptionPane.showMessageDialog(this, "Canción seleccionada: " + archivo.getName());
            }
        

            if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {

                java.io.File archivo = buscar.getSelectedFile();

                rutaCancion = archivo.getAbsolutePath();

                DATOS_MP3 datos = new DATOS_MP3();
                MUSICA musica = datos.leerDatos(rutaCancion);

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

                javax.swing.JOptionPane.showMessageDialog(this, "Canción agregada a la tabla");
            }

    }//GEN-LAST:event_BTN_BUSCAR_MUSICAActionPerformed

    private void BTN_PLAY_MUSICAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PLAY_MUSICAActionPerformed
        // TODO add your handling code here:
        
        
           int fila = tablaMusica.getSelectedRow();

    if (fila == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una cancion de la tabla");
        return;
    }

    String rutaSeleccionada = tablaMusica.getValueAt(fila, 7).toString();

    if (mp3.getRutaActual().equals("")) {

        mp3.reproducir(rutaSeleccionada);
        BTN_PLAY_MUSICA.setText("Pausa");

    } else if (mp3.getRutaActual().equals(rutaSeleccionada)) {

        mp3.playPausa();

        if (mp3.estaSonando()) {
            BTN_PLAY_MUSICA.setText("Pausa");
        } else {
            BTN_PLAY_MUSICA.setText("Play");
        }

    } else {

        mp3.reproducir(rutaSeleccionada);
        BTN_PLAY_MUSICA.setText("Pausa");
    
    }
        
    }//GEN-LAST:event_BTN_PLAY_MUSICAActionPerformed

    private void BTON_STOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTON_STOPActionPerformed
        // TODO add your handling code here:
        
         mp3.detener();
       
        
    }//GEN-LAST:event_BTON_STOPActionPerformed

    private void BTN_CARGARCAPETAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CARGARCAPETAActionPerformed
        // TODO add your handling code here:
        
     javax.swing.JFileChooser buscar = new javax.swing.JFileChooser();
    buscar.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

    int opcion = buscar.showOpenDialog(this);

    if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {

        java.io.File carpeta = buscar.getSelectedFile();

        tiempoCargaABB = 0;
        tiempoCargaAVL = 0;
        totalCancionesCargadas = 0;

        arbolABB = new ARBOL_ABB();
        arbolAVL = new ARBOL_AVL();

        cargarMusicaDeCarpeta(carpeta);

        DefaultTableModel modelo = (DefaultTableModel) tablaMusica.getModel();

        modelo.setRowCount(0);

        arbolABB.llenarTablaInorden(arbolABB.getRaiz(), modelo);

        double tiempoABBms = tiempoCargaABB / 1000000.0;
        double tiempoAVLms = tiempoCargaAVL / 1000000.0;

        javax.swing.JOptionPane.showMessageDialog(this,
                "Carga finalizada"
                + "\nCanciones cargadas: " + totalCancionesCargadas
                + "\nTiempo carga ABB: " + tiempoABBms + " ms"
                + "\nTiempo carga AVL: " + tiempoAVLms + " ms"
        );
    }
    }//GEN-LAST:event_BTN_CARGARCAPETAActionPerformed

    private void BTN_SIGUIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SIGUIENTEActionPerformed
        // TODO add your handling code here:
        
          MUSICA musica = listaDoble.siguiente();

            if (musica != null) {
                mp3.reproducir(musica.getRuta());
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No hay canción siguiente");
            }
    }//GEN-LAST:event_BTN_SIGUIENTEActionPerformed

    private void BTN_ANTERIORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ANTERIORActionPerformed
        // TODO add your handling code here:
        MUSICA musica = listaDoble.anterior();

            if (musica != null) {
                mp3.reproducir(musica.getRuta());
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No hay canción anterior");
            }

        
    }//GEN-LAST:event_BTN_ANTERIORActionPerformed

    private void BTN_HISTORIALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_HISTORIALActionPerformed
        // TODO add your handling code here:
        
              javax.swing.JOptionPane.showMessageDialog(
            this,
            historial.obtenerHistorial(),
            "Historial de reproducción",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
    );
    }//GEN-LAST:event_BTN_HISTORIALActionPerformed

    private void BTN_COLAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_COLAActionPerformed
        // TODO add your handling code here:
        
            int fila = tablaMusica.getSelectedRow();

        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una cancion de la tabla");
        } else {
            MUSICA musica = obtenerMusicaDeTabla(fila);

            cola.arriba(musica);

            javax.swing.JOptionPane.showMessageDialog(this, "Cancion agregada a la cola");
        }
    }//GEN-LAST:event_BTN_COLAActionPerformed

    private void COMBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBOXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_COMBOXActionPerformed

    private void btn_verCOLAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verCOLAActionPerformed
        // TODO add your handling code here:
        
         javax.swing.JOptionPane.showMessageDialog(
            this,
            cola.mostrarCola(),
            "Cola de reproducción",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
    );
    }//GEN-LAST:event_btn_verCOLAActionPerformed

    private void BNT_REPROCOLAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNT_REPROCOLAActionPerformed
        // TODO add your handling code here:
        
          MUSICA musica = cola.abajo();

    if (musica == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "La cola está vacía");
    } else {
        historial.apilar(musica);
        mp3.reproducir(musica.getRuta());

        javax.swing.JOptionPane.showMessageDialog(this,
                "Reproduciendo desde cola:\n" + musica.getNombre());
    }
    }//GEN-LAST:event_BNT_REPROCOLAActionPerformed

    private void BTN_CIRULARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CIRULARActionPerformed
        // TODO add your handling code here:
        
    MUSICA musica = listaCircular.siguiente();

    if (musica == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "No hay canciones cargadas");
    } else {
        historial.apilar(musica);
        mp3.reproducir(musica.getRuta());

        javax.swing.JOptionPane.showMessageDialog(this,
                "Reproduciendo en modo circular:\n" + musica.getNombre());
    }
    }//GEN-LAST:event_BTN_CIRULARActionPerformed

    private void BTN_CREARPLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CREARPLAYActionPerformed
        // TODO add your handling code here:
        
          String nombre = javax.swing.JOptionPane.showInputDialog(this, "Ingrese el nombre de la playlist");

        if (nombre != null && !nombre.equals("")) {

            VariosPlaylist.crearPlaylist(nombre);

            COMBOX.addItem(nombre);

            javax.swing.JOptionPane.showMessageDialog(this, "Playlist creada correctamente");
        }
        
    }//GEN-LAST:event_BTN_CREARPLAYActionPerformed

    private void VER_CAN_PLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VER_CAN_PLAYActionPerformed
        // TODO add your handling code here:
                                                   

            if (COMBOX.getSelectedItem() == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una playlist");
                return;
            }

            String nombrePlaylist = COMBOX.getSelectedItem().toString();

            PLAYLIST playlist = VariosPlaylist.buscarPlaylist(nombrePlaylist);

            if (playlist == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "No existe esa playlist");
            } else {
                PPLAYLIST ventana = new PPLAYLIST(playlist);
                ventana.setVisible(true);
            }
    }//GEN-LAST:event_VER_CAN_PLAYActionPerformed

    private void BTN_AGREGAR_CAN_PLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AGREGAR_CAN_PLAYActionPerformed
        // TODO add your handling code here:
        
 int fila = tablaMusica.getSelectedRow();

    if (fila == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una canción de la tabla");
        return;
    }

    if (COMBOX.getSelectedItem() == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una playlist");
        return;
    }

    String nombrePlaylist = COMBOX.getSelectedItem().toString();

    PLAYLIST playlist = VariosPlaylist.buscarPlaylist(nombrePlaylist);

    if (playlist == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "No existe esa playlist");
    } else {
        MUSICA musica = obtenerMusicaDeTabla(fila);

        playlist.agregar(musica);

        javax.swing.JOptionPane.showMessageDialog(this,
                "Canción agregada a la playlist: " + nombrePlaylist);
    }
    }//GEN-LAST:event_BTN_AGREGAR_CAN_PLAYActionPerformed

    private void BTN_ELIMINAR_PLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ELIMINAR_PLAYActionPerformed
        // TODO add your handling code here:
              if (COMBOX.getSelectedItem() == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una playlist");
                return;
            }

            String nombrePlaylist = COMBOX.getSelectedItem().toString();

            int respuesta = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar la playlist " + nombrePlaylist + "?",
                    "Confirmar",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            if (respuesta == javax.swing.JOptionPane.YES_OPTION) {

                VariosPlaylist.eliminarPlaylist(nombrePlaylist);

                COMBOX.removeItem(nombrePlaylist);

                javax.swing.JOptionPane.showMessageDialog(this, "Playlist eliminada");
            }

    }//GEN-LAST:event_BTN_ELIMINAR_PLAYActionPerformed

    private void BTN_ABRIPLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ABRIPLAYActionPerformed
        // TODO add your handling code here:
           if (COMBOX.getSelectedItem() == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una playlist");
        return;
    }

    String nombrePlaylist = COMBOX.getSelectedItem().toString();

    PLAYLIST playlist = VariosPlaylist.buscarPlaylist(nombrePlaylist);

    if (playlist == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "No existe esa playlist");
    } else {
        PPLAYLIST ventana = new PPLAYLIST(playlist);
        ventana.setVisible(true);
    }
    }//GEN-LAST:event_BTN_ABRIPLAYActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       UIManager.setLookAndFeel(new FlatDarkLaf());
        java.awt.EventQueue.invokeLater(() -> new RESPRODUCTORMP3().setVisible(true));
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BNT_REPROCOLA;
    private javax.swing.JButton BTN_ABRIPLAY;
    private javax.swing.JButton BTN_AGREGAR_CAN_PLAY;
    private javax.swing.JButton BTN_ANTERIOR;
    private javax.swing.JButton BTN_BUSCAR_MUSICA;
    private javax.swing.JButton BTN_CARGARCAPETA;
    private javax.swing.JToggleButton BTN_CIRULAR;
    private javax.swing.JButton BTN_COLA;
    private javax.swing.JButton BTN_CREARPLAY;
    private javax.swing.JButton BTN_ELIMINAR_PLAY;
    private javax.swing.JButton BTN_HISTORIAL;
    private javax.swing.JButton BTN_PLAY_MUSICA;
    private javax.swing.JButton BTN_SIGUIENTE;
    private javax.swing.JButton BTON_STOP;
    private javax.swing.JComboBox<String> COMBOX;
    private javax.swing.JButton VER_CAN_PLAY;
    private javax.swing.JButton btn_verCOLA;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JTable tablaMusica;
    // End of variables declaration//GEN-END:variables
 


}
