package GUI;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

import Grafo.*;
import Algoritmo.Dijsktra;

/**
 * Clase grafica
 */
public class Gui_Grafos extends javax.swing.JFrame {
    Grafo grafo = new Grafo();
    Nodo nodoInicio = null;
    Nodo nodoFin = null;
    private JPanel jPanel1;
    private JPanel jPanel2;

    /**
     * Constructor
     */
    public Gui_Grafos() {
        initComponents();
    }//constructor

    /***
     * Este es el metodo para iniciar todos los componentes que se ocupan en la parte grafica
     */
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dijkstra"));

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }//mouseClicked
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 629, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 417, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /***
     * Este metodo para a imprimir en el panel el grafo que se va construyendo
     */
    public void dibujarGrafo() {
        jPanel1.paint(jPanel1.getGraphics());
        dibujarArista();
        dibujarNodos();
    }//dibujarGrafo

    /***
     * Este metodo va a ir dibujando los nodos que el usuario va creando en el panel
     */
    public void dibujarNodos() {
        ArrayList<Nodo> listaNodo = grafo.getListaNodos();
        for (Nodo nodo : listaNodo) {
            nodo.getCirculo().dibujarCirculo(jPanel1.getGraphics());
        }//for
    }//dibujarNodo

    /***
     * Este metodo se va a encargar de dibujar la arista que une a los nodos
     */
    public void dibujarArista() {
        ArrayList<Nodo> listaNodo = grafo.getListaNodos();
        for (Nodo nodo : listaNodo) {
            ArrayList<Enlace> listaEnlace = nodo.getListaNodoAdyacente();
            if (listaEnlace != null) {
                for (Enlace enlace : listaEnlace) {
                    enlace.getArista().getLineaQuebrada().dibujarLineaQuebrada(jPanel1.getGraphics());
                }//for
            }//if
        }//for
    }//dibujarArista

    /***
     * Este metodo va a permitir que el usuarios cada vez que de click en los dos nodos que
     * quiere crear la arista ingrese el peso que este va a tener
     * @return
     */
    private int ingresarPeso() {
        String peso = JOptionPane.showInputDialog("Digite la distancia entre puntos");
        int intPeso = 0;
        try {
            intPeso = Integer.parseInt(peso);
        } catch (Exception ex) {
            intPeso = ingresarPeso();
        }//try-catch
        return intPeso;
    }//ingresarPeso

    /***
     * Este metodo va a eliminar un nodo que se le pida
     * @param x coordenada
     * @param y coordenada
     */
    private void eliminarNodo(int x, int y) {
        if (grafo.buscarNodo(x, y) != null) {//si se hace clic sobre un nodo
            Nodo temp = grafo.buscarNodo(x, y);
            grafo.eliminarAristasEntrante(temp);
            if (grafo.eliminarNodo(temp)) {
                JOptionPane.showMessageDialog(null, "Eliminado");
                dibujarGrafo();
            }//if

        }//if
    }//eliminarNodo

    /**
     * Estos son los mouse listener
     * @param evt
     */
    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        int x = evt.getX();
        int y = evt.getY();
        if (evt.isMetaDown()) {
//            eliminarNodo(x, y);
//            JOptionPane.showMessageDialog(this,"Clic derecho");
            if (grafo.buscarNodo(x, y) != null) {//si se hace clic sobre un nodo
                if (nodoInicio == null) {
                    grafo.reiniciarGrafoParaDisjktra();
                    grafo.reiniciarColores();
                    nodoInicio = grafo.buscarNodo(x, y);//nodoInicio lo pongo a apuntar al nodo donde hice clic
                    nodoInicio.getCirculo().setColor(Color.red);//Lo hago cambiar de color
//                JOptionPane.showMessageDialog(null,"Seleccione otro nodo para crear una arista");
                } else {//si nodoInicio ya estaba apunto a un nodo, lo preparo para crear arista
                    nodoFin = grafo.buscarNodo(x, y);
                    Dijsktra disjktra = new Dijsktra(grafo);
                    disjktra.ejecutar(nodoInicio);
                    disjktra.marcarRutaCorta(nodoFin, Color.red);
//                crearArista();
//
//                nodoInicio.getCirculo().setColor(Color.yellow);//lo regreso a su color original
//
                    nodoInicio = null;//null para poder crear mas arista
                    nodoFin = null;//null para poder crear mas arista
                }//if-else
            }//if


        } else {
            if (grafo.buscarNodo(x, y) != null) {//si se hace clic sobre un nodo
                if (nodoInicio == null) {
                    nodoInicio = grafo.buscarNodo(x, y);//nodoInicio lo pongo a apuntar al nodo donde hice clic
                    nodoInicio.getCirculo().setColor(Color.red);//Lo hago cambiar de color
//                JOptionPane.showMessageDialog(null,"Seleccione otro nodo para crear una arista");
                } else {//si nodoInicio ya estaba apunto a un nodo, lo preparo para crear arista
                    nodoFin = grafo.buscarNodo(x, y);
                    crearArista();

                    nodoInicio.getCirculo().setColor(Color.yellow);//lo regreso a su color original

                    nodoInicio = null;//null para poder crear mas arista
                    nodoFin = null;//null para poder crear mas arista
                }//if-esle
            } else {//Si no he hecho clic sobre un nodo
                crearNodo(x, y);//creo un nodo apartir de unas coordenadas
                //eliminarNodo(x,y);
            }//if-else
        }//if-else
        dibujarGrafo();
    }//GEN-LAST:event_jPanel1MouseClicked

    /***
     * Este metodo crea la arista que uno los dos nodos
     */
    private void crearArista() {
        int intPeso = ingresarPeso();
        Arista arista = new Arista();
        arista.setPeso(intPeso);
        Coordenadas c = new Coordenadas(100000, 100000);
        c.addCoordenada(nodoInicio.getCirculo().getX() + (nodoInicio.getCirculo().getDiametro() / 2), nodoInicio.getCirculo().getY() + (nodoInicio.getCirculo().getDiametro() / 2));
        c.addCoordenada(nodoFin.getCirculo().getX() + (nodoInicio.getCirculo().getDiametro() / 2), nodoFin.getCirculo().getY() + (nodoInicio.getCirculo().getDiametro() / 2));
        LineaQuebrada lq = new LineaQuebrada(c);
        arista.setLineaQuebrada(lq);
        grafo.crearEnlacesNoDirigido(nodoInicio, nodoFin, arista);

    }//crearArista

    /***
     * Este metodo crea el nodo el la posicion que el usuario hubiera dado click
     * @param x coordenada
     * @param y coordenada
     */
    private void crearNodo(int x, int y) {
        Coordenadas c = new Coordenadas(100000, 100000, x, y);
        String dato = JOptionPane.showInputDialog("Digite un dato o Nombre de la coordenada");
        if (dato != null) {
            dato = dato.toUpperCase();//por que lo quiero todo en mayusculas
            Nodo nodo = new Nodo(dato, c);
            nodo.getCirculo().setDiametro(12);
            nodo.getCirculo().setEtiqueta(nodo.getDato() + "");
            if (grafo.adjuntarNodo(nodo)) {
                nodo.getCirculo().dibujarCirculo(jPanel1.getGraphics());
            } else {
            }//if-else
            nodoInicio = null;
            nodoFin = null;
        }//if
    }//crearNodo

    /**
     * @param args the command line arguments
     */
    /**public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Gui_Grafos().setVisible(true);
            }
        });
    }**/

    // Variables declaration - do not modify//GEN-BEGIN:variables

    // End of variables declaration//GEN-END:variables
}//fin clase Gui
