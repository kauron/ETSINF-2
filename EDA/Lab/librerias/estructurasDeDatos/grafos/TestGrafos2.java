package librerias.estructurasDeDatos.grafos;
/**
 * Prova basica de l'algorisme de E. Dijkstra i de la recuperacio
 * dels camins minims.
 *
 * @author (EDA)
 * @version (curs 2015 - 2016)
 */
public class TestGrafos2 {

    public static void main(String[] args) {
        // construim un GrafoDirigido amb 7 vertex
        Grafo g = new GrafoDirigido(7);

        // afegim les aristes:
        g.insertarArista(0, 1, 3); //g.insertarArista(1,0,3);
        g.insertarArista(0, 2, 6); //g.insertarArista(2,0,6);
        g.insertarArista(1, 3, 1); //g.insertarArista(3,1,1);
        g.insertarArista(2, 3, 1); g.insertarArista(3, 2, 1);
        g.insertarArista(2, 4, 1); //g.insertarArista(4,2,1);
        g.insertarArista(3, 4, 5); //g.insertarArista(4,3,5);
        g.insertarArista(5, 6, 2); //g.insertarArista(6,5,2);

        // escrivim el GrafoDirigido:
        System.out.println("=======================================");
        System.out.println("El GrafoDirigido es: ");
        System.out.println(g);
        System.out.println("=======================================");

        // executem dikjstra des del 0:
        g.dijkstra(0);

        // escrivim les distancies minimes:
        // ha d'escriure: 0.0 3.0 5.0 4.0 6.0 INF INF
        System.out.println("Array de distancies minimes (des del 0): ");
        for (int i = 0; i < g.numVertices(); i++) {
            System.out.println("al " + i + ": " + g.distanciaMin[i]);
        }
        System.out.println(">> S'ha d'escriure: "
            + "0.0 3.0 5.0 4.0 6.0 INF INF");
        System.out.println("-------------------------------------------");

        // escrivim l'Array de camins minims:
        // ha d'escriure: -1 0 3 1 2 -1 -1
        System.out.println("\nArray de camins minims (des del 0): ");
        for (int i = 0; i < g.numVertices(); i++) {
            System.out.println("al " + i + ": " + g.caminoMin[i]);
        }
        System.out.println(">> S'ha d'escriure: " + "-1 0 3 1 2 -1 -1");
        System.out.println("-------------------------------------------");

        // cami minim del vertex 0 al 4:
        // ha d'escriure: [0, 1, 3, 2, 4]
        System.out.print("\nCami minim del vertex 0 al 4: ");
        System.out.println(g.caminoMinimo(0, 4));
        System.out.println(">> S'ha d'escriure: " + "[0, 1, 3, 2, 4]");
        System.out.println("-------------------------------------------");

        // cami minim del vertex 0 al 5:
        // ha d'escriure: []
        System.out.print("Cami minim del vertex 0 al 5: ");
        System.out.println(g.caminoMinimo(0, 5));
        System.out.println(">> S'ha d'escriure: " + "[]");
        System.out.println("-------------------------------------------");
    }

}
