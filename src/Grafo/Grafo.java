package Grafo;
import java.awt.Color;
import java.util.ArrayList;

public class Grafo {
    private ArrayList<Nodo> listaNodo;
    private Nodo adyacente;

    public Grafo() {
        listaNodo = new ArrayList<Nodo>();
    }

    public boolean adjuntarNodo(Nodo nodo) {
        Nodo nodoTemp = buscarNodo(nodo.getDato());
        if (nodoTemp == null) {
            listaNodo.add(nodo);
            return true;
        } else {
            return false;
        }//if-else
    }//adjuntarNodo

    /***
     * Este metodo lo que hace es generar los enlaces dirigidos a los demas nodos
     * @param padre se ocupa del nodo padre
     * @param adyacente se ocupa del nodo del lado
     * @param arista y se ocupa de la arista
     */
    public void crearEnlacesDirigido(Nodo padre, Nodo adyacente, Arista arista) {
        if (padre != null && adyacente != null) {
            padre.addNodoAdyacente(arista, adyacente);
        }//if
    }//crearEnlacesDirigido

    /***
     * Este metodo lo que hace es generar los enlaces no dirigidos o los que no se conectan
     * @param padre se ocupa del nodo padre
     * @param adyacente se ocupa del nodo del lado
     * @param arista y se ocupa de la arista
     */
    public void crearEnlacesNoDirigido(Nodo padre, Nodo adyacente, Arista arista) {
        crearEnlacesDirigido(padre, adyacente, arista);
        crearEnlacesDirigido(adyacente, padre, arista);
    }//crearEnlacesNoDirigido

    /***
     * Este metodo lo que hace es buscar un nodo que se requiera
     * @param dato va a ser el nodo que se va a buscar
     * @return
     */
    public Nodo buscarNodo(Object dato) {
        Nodo temp = null;
        if (dato != null) {
            for (Nodo nodo : listaNodo) {
                if (dato.equals(nodo.getDato())) {
                    temp = nodo;
                }//if
            }//for
        }//if
        return temp;
    }//buscarNodo

    /***
     * Este metodo va a buscar un nodo en una posicion
     * @param x cordenada
     * @param y cordenada
     * @return
     */
    public Nodo buscarNodo(int x, int y) {
        Nodo nodoAuxiliar = null;
        for (int i = 0; i < listaNodo.size(); i++) {
            int xNodo = listaNodo.get(i).getCirculo().getX();
            int yNodo = listaNodo.get(i).getCirculo().getY();
            if (x > xNodo && x < (xNodo + listaNodo.get(i).getCirculo().getDiametro())) {
                if (y > yNodo && y < (yNodo + listaNodo.get(i).getCirculo().getDiametro())) {
                    nodoAuxiliar = listaNodo.get(i);
                    break;
                }//if
            }//if
        }//for
        return nodoAuxiliar;
    }//buscarNodo

    /***
     * Este metodo es para ver los nodos adyacentes
     * @param n1 se solicita un nodo
     * @param n2 se solicita otro nodo
     * @return
     */
    public boolean isAdyacente(Nodo n1, Nodo n2) {
        boolean aux = false;
        ArrayList<Enlace> listaAristas = n1.getListaNodoAdyacente();
        if (listaAristas != null) {
            for (int i = 0; i < listaAristas.size(); i++) {
                if (listaAristas.get(i).getNodo() == n2) {
                    aux = true;
                }//if
            }//for
        }//if
        return aux;
    }//isAdyacente

    /***
     *
     * @param datoNodoInicio
     * @param datoNodoDestino
     * @return
     */

    public boolean isAdyacente(Object datoNodoInicio, Object datoNodoDestino) {
        boolean aux = false;
        Nodo n1 = buscarNodo(datoNodoInicio);
        Nodo n2 = buscarNodo(datoNodoDestino);
        ArrayList<Enlace> listaAristas = n1.getListaNodoAdyacente();
        if (listaAristas != null) {
            for (int i = 0; i < listaAristas.size(); i++) {
                if (listaAristas.get(i).getNodo() == n2) {
                    aux = true;
                }//if
            }//for
        }//if
        return aux;
    }//isAdyacente

    /***
     *
     */
    public void reiniciarGrafoParaDisjktra() {
        for (Nodo n : listaNodo) {
            n.setMarca(false);
            n.setNodoAntecesorDisjktra(null);
            n.setLongitudCamino(-1);
        }//for
    }//reiniciarGrafoParaDisjktra

    /***
     *
     * @param nodo
     * @return
     */
    public boolean eliminarNodo(Nodo nodo) {
        boolean retornado = false;
        if (nodo != null) {
            retornado = listaNodo.remove(nodo);
        }//if
        return retornado;
    }//eliminarNodo

    /***
     * Este metodo es para reiniciar los colores en la parte grafica
     */
    public void reiniciarColores() {
        if (listaNodo != null) {
            for (Nodo nodo : listaNodo) {
                nodo.getCirculo().setColor(Color.yellow);
                ArrayList<Enlace> la = nodo.getListaNodoAdyacente();
                if (la != null) {
                    for (Enlace enlace : la) {
                        if (enlace.getArista().isHabilitado()) {
                            enlace.getArista().getLineaQuebrada().setColor(Color.black);
                            enlace.getArista().getLineaQuebrada().setGrosorLinea(1);
                        }//if
                    }//for
                }//if
            }//for
        }//if
    }//reiniciarColores

    /***
     * Este metodo es para buscar la arista entrante
     */
    public ArrayList<Arista> aristasEntrante(Nodo nodo) {
        ArrayList<Arista> listaArista = null;
        for (Nodo n : listaNodo) {
            ArrayList<Enlace> enlaces = n.getListaNodoAdyacente();
            if (enlaces != null) {
                listaArista = new ArrayList<Arista>();
                for (Enlace e : enlaces) {
                    if (e.getNodo() == nodo) {
                        listaArista.add(e.getArista());
                    }//if
                }//for
            }//if
        }//for
        return listaArista;
    }//aristasEntrante

    /***
     * Este es para ver la arista saliente de un nodo, funcion recursiva
     * @param nodo se necesita de un nodo
     * @return
     */
    public ArrayList<Arista> aristasSaliente(Nodo nodo) {
        ArrayList<Arista> listaArista = null;
        if (nodo != null) {
            if (listaNodo.contains(nodo)) {
                ArrayList<Enlace> listaEnlace = nodo.getListaNodoAdyacente();
                if (listaArista != null) {
                    listaArista = new ArrayList<Arista>();
                    for (Enlace e : listaEnlace) {
                        listaArista.add(e.getArista());
                    }//for
                }//if
            }//if
        }//if
        return listaArista;
    }//aristasSaliente

    /***
     * Este metodo es para eliminar alguna arista de un nodo
     * @param nodo se necesita de un nodo
     */
    private void eliminarAristas(Nodo nodo) {
        ArrayList<Arista> aristas = aristasSaliente(nodo);
        for (Arista a : aristas) {
            a = null;
        }//for
    }//eliminarAristas

    /**
     * metodo de prueba
     * @param nodo
     */
    public void eliminarAristasSalientes(Nodo nodo) {
        ArrayList<Arista> aristas = aristasSaliente(nodo);
        eliminarAristas(nodo);
    }//eliminarAristasSalientes

    /***
     * Metodo para eliminar la arista
     * @param nodo se necesita de un nodo
     */
    public void eliminarAristasEntrante(Nodo nodo) {
        ArrayList<Arista> aristas = aristasEntrante(nodo);
        eliminarAristas(nodo);
    }//eliminarAristasEntrante

    /***
     * Getters and Setters
     *
     */

    public ArrayList<Nodo> getAdyacentes(Object dato) {
        ArrayList<Nodo> lista = null;
        Nodo principal = buscarNodo(dato);
        ArrayList<Enlace> aristas = principal.getListaNodoAdyacente();
        if (aristas != null) {
            for (int i = 0; i < aristas.size(); i++) {
                lista.add(aristas.get(i).getNodo());
            }
        }
        return lista;
    }
    public ArrayList<Nodo> getListaNodos() {
        return listaNodo;
    }
    public Arista getArista(Object datoNodo1, Object datoNodo2) {
        return getArista(buscarNodo(datoNodo1), buscarNodo(datoNodo2));
    }
    public Arista getArista(String nombreVia) {
        Arista aux = null;
        if (nombreVia != null) {
            ArrayList<Nodo> listaN = listaNodo;
            for (Nodo nd : listaN) {
                ArrayList<Enlace> lA = nd.getListaNodoAdyacente();
                for (Enlace enlace : lA) {
                    if (enlace.getArista().getNombreArista().equals(nombreVia)) {
                        aux = enlace.getArista();
                    }
                }
            }
        }
        return aux;
    }
    public Arista getArista(Nodo n1, Nodo n2) {
        Arista aux = null;
        if (isAdyacente(n1, n2)) {
            ArrayList<Enlace> listaAristas = n1.getListaNodoAdyacente();
            for (int i = 0; i < listaAristas.size(); i++) {
                if (listaAristas.get(i).getNodo() == n2) {
                    aux = listaAristas.get(i).getArista();
                }
            }
        } else if (isAdyacente(n2, n1)) {
            aux = getArista(n2, n1);
        }
        return aux;
    }
    public Enlace getEnlace(Object datoNodo1, Object datoNodo2) {
        Enlace aux = null;
        if (isAdyacente(datoNodo1, datoNodo2)) {
            Nodo n1 = buscarNodo(datoNodo1);
            Nodo n2 = buscarNodo(datoNodo2);
            ArrayList<Enlace> listaAristas = n1.getListaNodoAdyacente();
            for (int i = 0; i < listaAristas.size(); i++) {
                if (listaAristas.get(i).getNodo() == n2) {
                    aux = listaAristas.get(i);
                }
            }
        } else if (isAdyacente(datoNodo2, datoNodo1)) {
            aux = getEnlace(datoNodo2, datoNodo1);
        }
        return aux;
    }

}//fin clase grafo