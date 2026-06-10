/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_final_smart_player;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author grija
 */
public class VISOR_JGRAPHX extends JFrame{
    
    mxGraph graph;
    Object parent;
    HashMap<String, Object> nodos;
    mxGraphComponent componente;

    public VISOR_JGRAPHX() {
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        graph = new mxGraph();
        parent = graph.getDefaultParent();
        nodos = new HashMap<String, Object>();
    }

    public void mostrarABB(NODO_ABB raiz) {

        if (raiz == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "El arbol ABB esta vacio");
            return;
        }

        setTitle("Visualizacion completa del arbol ABB");

        graph.getModel().beginUpdate();

        try {
            agregarABB(raiz);
            mxCompactTreeLayout layout = new mxCompactTreeLayout(graph, false);
            layout.setLevelDistance(80);
            layout.setNodeDistance(30);
            layout.execute(parent);
        } finally {
            graph.getModel().endUpdate();
        }

        componente = new mxGraphComponent(graph);
        componente.setConnectable(false);
        componente.getGraph().setCellsEditable(false);
        componente.getGraph().setCellsMovable(false);
        componente.getGraph().setCellsResizable(false);

        componente.setPreferredSize(new Dimension(5000, 3000));

        JPanel panelBotones = new JPanel();

        JButton btnZoomMas = new JButton("Zoom +");
        JButton btnZoomMenos = new JButton("Zoom -");
        JButton btnZoomNormal = new JButton("Zoom normal");

        btnZoomMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componente.zoomIn();
            }
        });

        btnZoomMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componente.zoomOut();
            }
        });

        btnZoomNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componente.zoomTo(1.0, true);
            }
        });

        panelBotones.add(btnZoomMas);
        panelBotones.add(btnZoomMenos);
        panelBotones.add(btnZoomNormal);

        add(panelBotones, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(componente);
        add(scroll, BorderLayout.CENTER);

        setVisible(true);
    }

    public void mostrarAVL(NODO_AVL raiz) {

        if (raiz == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "El arbol AVL esta vacio");
            return;
        }

        setTitle("Visualizacion completa del arbol AVL");

        graph.getModel().beginUpdate();

        try {
            agregarAVL(raiz);
            mxCompactTreeLayout layout = new mxCompactTreeLayout(graph, false);
            layout.setLevelDistance(80);
            layout.setNodeDistance(30);
            layout.execute(parent);
        } finally {
            graph.getModel().endUpdate();
        }

        componente = new mxGraphComponent(graph);
        componente.setConnectable(false);
        componente.getGraph().setCellsEditable(false);
        componente.getGraph().setCellsMovable(false);
        componente.getGraph().setCellsResizable(false);

        componente.setPreferredSize(new Dimension(5000, 3000));
        JPanel panelBotones = new JPanel();

        JButton btnZoomMas = new JButton("Zoom +");
        JButton btnZoomMenos = new JButton("Zoom -");
        JButton btnZoomNormal = new JButton("Zoom normal");

        btnZoomMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componente.zoomIn();
            }
        });

        btnZoomMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componente.zoomOut();
            }
        });

        btnZoomNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componente.zoomTo(1.0, true);
            }
        });

        panelBotones.add(btnZoomMas);
        panelBotones.add(btnZoomMenos);
        panelBotones.add(btnZoomNormal);

        add(panelBotones, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(componente);
        add(scroll, BorderLayout.CENTER);

        setVisible(true);
    }

    public Object agregarABB(NODO_ABB nodo) {

        if (nodo == null) {
            return null;
        }

        String id = nodo.musica.getRuta();

        Object nodoGrafico = graph.insertVertex(
                parent,
                id,
                cortarNombre(nodo.musica.getNombre()),
                20,
                20,
                120,
                40,
                "fillColor=#dc143c;fontColor=#ffffff;strokeColor=#ffffff"
        );

        nodos.put(id, nodoGrafico);

        if (nodo.hijoIzquierdo != null) {
            Object hijoIzq = agregarABB(nodo.hijoIzquierdo);
            graph.insertEdge(parent, null, "Izq", nodoGrafico, hijoIzq);
        }

        if (nodo.hijoDerecho != null) {
            Object hijoDer = agregarABB(nodo.hijoDerecho);
            graph.insertEdge(parent, null, "Der", nodoGrafico, hijoDer);
        }

        return nodoGrafico;
    }

    public Object agregarAVL(NODO_AVL nodo) {

        if (nodo == null) {
            return null;
        }

        String id = nodo.musica.getRuta();

        Object nodoGrafico = graph.insertVertex(
                parent,
                id,
                cortarNombre(nodo.musica.getNombre()),
                20,
                20,
                120,
                40,
                "fillColor=#1DB954;fontColor=#ffffff;strokeColor=#ffffff"
        );

        nodos.put(id, nodoGrafico);

        if (nodo.hijoIzquierdo != null) {
            Object hijoIzq = agregarAVL(nodo.hijoIzquierdo);
            graph.insertEdge(parent, null, "Izq", nodoGrafico, hijoIzq);
        }

        if (nodo.hijoDerecho != null) {
            Object hijoDer = agregarAVL(nodo.hijoDerecho);
            graph.insertEdge(parent, null, "Der", nodoGrafico, hijoDer);
        }

        return nodoGrafico;
    }

    public String cortarNombre(String nombre) {
        if (nombre == null) {
            return "Sin nombre";
        }

        if (nombre.length() > 15) {
            return nombre.substring(0, 15);
        }

        return nombre;
    }
    
}
