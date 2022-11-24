package Algoritmo;

import java.awt.Color;
import java.util.ArrayList;

import Grafo.Arista;
import Grafo.Enlace;
import Grafo.Grafo;
import Grafo.Nodo;

/***
 * Clase para el algoritmo Dijsktra
 */
public class Dijsktra {
    Grafo grafo;
    ListaNodo listaNodosAdyacentes;
    ArrayList<Arista> aux = new ArrayList<Arista>();

    /***
     * Metodo constructor
     * @param grafo
     */
    public Dijsktra(Grafo grafo) {
        this.grafo = grafo;
        listaNodosAdyacentes = new ListaNodo();
    }//constructor

    /***
     * Este metodo va haciendo las comparaciones y adjuntando con los nodos adyacentes
     * para ir creadno el camino
     * @param nodo
     */
    private void llenarConAdyacentes(Nodo nodo) {
        if (nodo != null) {
            ArrayList<Enlace> listaAux = nodo.getListaNodoAdyacente();
            if (listaAux != null) {
                for (Enlace enlace : listaAux) {
                    Nodo aux2 = enlace.getNodo();
                    if (!aux2.isMarca()) {

                        if (listaNodosAdyacentes.isContenido(aux2)) {
                            int longitud = nodo.getLongitudCamino() + enlace.getArista().getPeso();
                            if (aux2.getLongitudCamino() > longitud) {
                                aux2.setLongitudCamino(longitud);
                                aux2.setNodoAntecesorDisjktra(nodo);
                            }
                        } else {
                            aux2.setLongitudCamino(nodo.getLongitudCamino() + enlace.getArista().getPeso());
                            aux2.setNodoAntecesorDisjktra(nodo);
                            listaNodosAdyacentes.add(aux2);
                        }//if-else

                    }//if
                }//for
            }//if
        }//if
    }//llenarConAdyacentes

    /***
     *  Este metodo hace la ejecucion para ubicarse en el nodo a partir del cuál se quiere empezar
     *  el recorrido
     * @param nodoInicio
     */
    public void ejecutar(Nodo nodoInicio) {
        nodoInicio.setLongitudCamino(0);
        if (nodoInicio != null) {
            listaNodosAdyacentes = new ListaNodo();
            listaNodosAdyacentes.add(nodoInicio);
            while (!listaNodosAdyacentes.isEmpty()) {
                Nodo menor = listaNodosAdyacentes.buscarMenor();
                menor.setMarca(true);
                listaNodosAdyacentes.remove(menor);
                llenarConAdyacentes(menor);
            }//while
        }//if
    }//ejecutar
    /***
     * Este metodo se encarga de buscar la ruta mas corta
     * @param nodoFinal recibe esta variable para tomar el ultimo nodo que se seecciono para ver con todas las rutas que hay
     */
    private void rutaCorta(Nodo nodoFinal) {
        aux.clear();
        Nodo nAux = nodoFinal;
        while (nAux.getNodoAntecesorDisjktra() != null) {
//        aux.add(grafo.getArista(nAux.getCapital().getNombreCiudad(),
//                nAux.getNodoAntecesorDisjktra().getCapital().getNombreCiudad()));
            aux.add(grafo.getArista(nAux,
                    nAux.getNodoAntecesorDisjktra()));
            nAux = nAux.getNodoAntecesorDisjktra();
        }//while

    }//rutaCorta
    /***
     * Este metodo se encarga de marcar la ruta desde el primero nodo elegido hasta el ultimo
     */
    public void marcarRutaCorta(Nodo nodoFinal, Color color) {
        if (nodoFinal != null) {
            rutaCorta(nodoFinal);
            for (int i = 0; i < aux.size(); i++) {
                if (!aux.isEmpty()) {
                    aux.get(i).getLineaQuebrada().setColor(color);
                    aux.get(i).getLineaQuebrada().setGrosorLinea(4);
                }//if
            }//for
        }//if
    }//marcaRutaCorta

}//fin clase
