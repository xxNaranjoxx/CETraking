import Grafo.Grafo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Grafo G = new Grafo();
        int o = 0;

        do {

            G.Introducir();
            G.Muestra();
            G.Conexo();
            G.Grado();
            System.out.println("Desea buscar un camino en este grafo \n1.-SI\n2.-NO");
            int op = scan.nextInt();
            if (op == 1) {
                System.out.println("De el vertice de partida");
                int i = scan.nextInt();
                System.out.println("De el vertice a llegar");
                int j = scan.nextInt();
                G.Camino(i, j);
            }

            System.out.println("^*****GRAFO EVALUADO*****");
            System.out.println("\nDesea introducir otro grafo \n1.-SI\n2.-NO");
            o = scan.nextInt();
        } while (o == 1);
        G.Muestra();
    }


}
