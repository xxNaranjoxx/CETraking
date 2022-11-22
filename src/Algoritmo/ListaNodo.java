package Algoritmo;

import java.util.ArrayList;

import Grafo.Nodo;

public class ListaNodo extends ArrayList<Nodo> {
    public ListaNodo() {
        super();
    }//constructor

    public Nodo buscarMenor() {
        Nodo aux = new Nodo();
        aux.setLongitudCamino(9999999);

        for (Nodo nodo : this) {
            if (nodo.getLongitudCamino() < aux.getLongitudCamino()) {
                aux = nodo;
            }//if
        }//for

        return aux;
    }//buscarMenor

    /***
     * Este metodo es para saber si ese nodo está en la lista
     * @param nodo se va a ocupar de un nodo para preguntar
     * @return true si está y false si no está
     */
    public boolean isContenido(Nodo nodo) {
        boolean retornado = false;
        for (Nodo n : this) {
            if (n == nodo) {
                retornado = true;
            }//if
        }//for
        return retornado;
    }//isCotenido

}//fin clase