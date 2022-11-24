package Algoritmo;

import java.util.ArrayList;
import java.util.List;

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


    /*
        int length;


        public List<Integer> names = new ArrayList<>();


        public void jiji() {

        }

        void sort(List<Integer> array) {
            if (array == null || array.size() == 0) {
                return;
            }
            this.names = array;
            this.length = array.size();
            quickSort(0, length - 1);
            names.remove(null);
            System.out.println("Feo" + names);
        }

        void quickSort(int lowerIndex, int higherIndex) {
            int i = lowerIndex;
            int j = higherIndex;
            String pivot = this.names.get(lowerIndex + (higherIndex - lowerIndex) / 2);

            while (i <= j) {
                while (this.names.get(i).compareToIgnoreCase(pivot) < 0) {
                    i++;
                }

                while (this.names.get(j).compareToIgnoreCase(pivot) > 0) {
                    j--;
                }

                if (i <= j) {
                    exchangeNames(i, j);
                    i++;
                    j--;
                }
            }
            //call quickSort recursively
            if (lowerIndex < j) {
                quickSort(lowerIndex, j);
            }
            if (i < higherIndex) {
                quickSort(i, higherIndex);
            }
        }

        void exchangeNames(int i, int j) {
            String temp = this.names.get(i);
            this.names.set(i, this.names.get(j));
            this.names.set(j, temp);
        }
    */


}//fin clase