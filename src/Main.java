import GUI.Gui_Grafos;

public class Main {

    public static void main(String[] args) {

        /**
         * Se crea el hilo para estar actualizadno y recibiendo informacion de GUI
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Gui_Grafos().setVisible(true);
            }
        });

        /***
        int u, z, origen = 0,nodos = 0, caso = 0, existe = 0 ;
        boolean t, w = true, y = true;
        while (w == true){
            Scanner teclado = new Scanner(System.in);
            System.out.println("----HOLA----");
            System.out.println("1 Para digitar los nodos");
            System.out.println("2 Por si quieres ver el ejemplo");
            System.out.println("3 Para salir");
            System.out.println("------------");

            do {
                t = false;
                try {
                    System.out.println("Selecciona el tipo de caso: ");
                    caso = teclado.nextInt();
                }catch (Exception e){
                    System.out.println("Solo se aceptan numeros naturales");
                    t = true;
                    teclado.next();
                }
            }while (t);

            switch (caso){
                case 1:
                    while (y == true){
                        System.out.println("Seleccionaste 1");
                        do {
                            t = false;
                            try{
                                System.out.println("Digita el numero de nodos: ");
                                nodos = teclado.nextInt();
                                
                            }catch (Exception e){

                                System.out.println("Solo se aceptan numeros naturales.");
                                t= true;
                                teclado.next();
                            }//try-catch
                        }while (t);
                        Dijkstra2 abc = new Dijkstra2();
                        System.out.println("Digite la matriz de costos separado por espacios o digitlos uno por uno");
                        for (u = 1; u <= nodos; u++) {
                            for (z = 1; z <= nodos; z++) {

                                do{
                                    t = false;
                                    try{
                                        abc.precio[u][z] = teclado.nextInt();
                                    }catch (Exception e){
                                        System.out.println("Solo se aceptan numeros naturales");
                                        t = true;
                                        teclado.next();
                                    }
                                }while (t);
                                if (abc.precio[u][z] == 0){
                                    abc.precio[u][z] = 999;
                                }//if
                            }//for z
                        }//for u
                        do {
                            t = false;
                            try {
                                System.out.println("Digite el origen de la vertice: ");
                                origen = teclado.nextInt();
                            }catch (Exception e){
                                System.out.println("Solo se aceptan numeros naturales.");
                                t= true;
                                teclado.next();
                            }//try-catch
                        }while (t);//termina do

                        abc.calcula(nodos,origen);
                        System.out.println("El camino mas corto desde el origen " + origen + " a todos los demas vertices son:");
                        for (u = 1; u <= nodos; u++) {
                            if (u != origen){
                                System.out.println("Origen: " + origen + " Destino: " + u + " Costos minimo: " + abc.dist[u]);
                                System.out.println("");
                            }//if
                        }//for
                        System.out.println(abc.dist[u]);
                        System.out.println("Selecciona 1 para regresar al menu principal");
                        System.out.println("Selecciona algun numero para volver a empezar");
                        do{
                            t = false;
                            try{
                                System.out.println("Selecciona el tipo de caso: ");
                                existe = teclado.nextInt();
                            }catch (Exception e){
                                System.out.println("Solo se acepta numeros naturales.");
                                t = true;
                                teclado.next();
                            }//fin try
                        } while (t);
                        if (existe == 1 ) {
                            y= false;
                        }
                        
                    }
                    break;
                case 2:
                    System.out.println("caso 2");
                    break;
                case 3:
                    System.out.println("Sellecionaste salir");
                    System.exit(0);
                    break;
            }


        }**/

        /**Scanner scan = new Scanner(System.in);
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
        } while (o == 1);**/
    }


}//clase main
