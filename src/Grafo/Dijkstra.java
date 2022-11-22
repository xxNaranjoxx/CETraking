package Grafo;

import javax.swing.*;
import java.util.Scanner;

public class Dijkstra {

    public int precio[][] = new int[10][10];
    public int dist[] = new int[10]; //almacenamiento de las distancias



    public void calcula(int x, int y) {
        int indi [] = new int[x+1];
        int u,costomin = 1, c,d,minimo; // variable con las distancia

        for (u = 1; u <= x; u++) {
            indi[u] = 0;
            this.dist[u] = this.precio[y][u]; //precio
        }//for
        c= 2;
        while (c<=x){
            minimo = 99;
            for (d = 1; d <= x; d++) {

                if (this.dist[d]< minimo && indi[d] != 1){
                    minimo = this.dist[u];
                    costomin = d;
                }//if
            }//for
            indi[costomin]= 1;
            c++;
            for (d = 1; d <= x; d++) {

                if (this.dist[costomin]+ this.precio[costomin][d] < this.dist[d] && indi[d] !=1) {
                    this.dist[d] = this.dist[costomin] + this.precio[costomin][d];
                }//if

            }//for

        }//while


    }//calcula
}//fin clase dijkstra
