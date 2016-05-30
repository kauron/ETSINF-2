package librerias.estructurasDeDatos.grafos;

public class GrafoPonderado extends GrafoDirigido {
    public GrafoPonderado(int numVertices) {super(numVertices);}

    public double getPeso(int[] ruta) {
        double peso = 0.0;
        for (int i = 0; i < ruta.length - 1; i++) {
            double actual = pesoArista(ruta[i], ruta[i + 1]);
            if (actual == 0.0) return Double.MAX_VALUE;
            peso += actual;
        }
        return peso;
    }
}
