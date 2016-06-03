package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.jerarquicos.PriorityQColaPrioridad;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.modelos.ListaConPI;

import java.util.Arrays;
import java.util.NoSuchElementException;

/** Clase abstracta Grafo: Base de la jerarquia Grafo, que define el
 *  comportamiento de un grafo.<br>
 *  No es una interfaz porque incluye el codigo de las operaciones de un
 *  grafo que son independientes tanto de su tipo como de su implementacion.<br>
 *
 *  @version Mayo 2016
 */
public abstract class Grafo {

    // protected static final int INFINITO = (Integer.MAX_VALUE)/3;
    // Distancia a un vertice aun no alcanzado

    protected static final double INFINITO = Double.POSITIVE_INFINITY;

    protected int[] visitados;
    //Para las operaciones de recorrido
    protected int ordenVisita;
    //Orden de visita de los vertices en los recorridos
    protected Cola<Integer> q;
    //Para recorridos en anchura del grafo

    protected double[] distanciaMin;
    // Distancia minima del vertice origen al resto de vertices
    protected int[] caminoMin;
    // Vertice anterior en el camino mas corto

    /** Devuelve el numero de vertices del grafo
      * @return int numero de vertices del grafo
      */
    public abstract int numVertices();

    /** Devuelve el numero de aristas del grafo
      * @return int numero de aristas del grafo
      */
    public abstract int numAristas();

    /** Comprueba si la arista (i,j) esta en un grafo.
      * @param i    Vertice origen
      * @param j    Vertice destino
      * @return boolean true si (i,j) est� en el grafo y false en caso contrario
      */
    public abstract boolean existeArista(int i, int j);

    /** Devuelve el peso de la arista (i,j) de un grafo, 0 si dicha arista
      * no esta en el grafo.
      * @param i    Vertice origen
      * @param j    Vertice destino
      * @return double Peso de la arista (i,j), 0 si no existe.
      */
    public abstract double pesoArista(int i, int j);

    /** Si no esta, inserta la arista (i, j) en un grafo no Ponderado.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     */
    public abstract void insertarArista(int i, int j);

    /** Si no esta, inserta la arista (i, j) de peso p en un grafo Ponderado.
       * @param i    Vertice origen
       * @param j    Vertice destino
       * @param p    Peso de la arista (i,j)
     */
    public abstract void insertarArista(int i, int j, double p);

    /** Devuelve una ListaConPI que contiene los adyacentes al vertice i
      * @param i Vertice del que se obtienen los adyacentes
      * @return ListaConPI con los vertices adyacentes a i
     */
    public abstract ListaConPI<Adyacente> adyacentesDe(int i);

    /** Devuelve un String con cada uno de los vertices de un grafo y sus
     *  adyacentes, en orden de insercion
     * @return  String que representa a un grafo
     */
    public String toString() {
        String res = "";
        for (int  i = 0; i < numVertices(); i++) {
            res += "Vertice: " + i;
            ListaConPI<Adyacente> l = adyacentesDe(i);
            if (l.esVacia()) { res += " sin Adyacentes "; }
            else { res += " con Adyacentes "; }
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                res +=  l.recuperar() + " ";
            }
            res += "\n";
        }
        return res;
    }


    /** Establece los caminos minimos con pesos desde el vertice origen a
     * todos los restantes.
     * @param  origen, vertice origen de los caminos minimos
     * @return void, los caminos minimos y sus longitudes se guardan
     * codificados en los arrays caminoMin y distanciaMin, respectivamente
     */
    protected void dijkstra(int origen) {
        distanciaMin = new double[numVertices()];
        caminoMin = new int[numVertices()];
        visitados = new int[numVertices()];

        Arrays.fill(distanciaMin, Double.POSITIVE_INFINITY);
        Arrays.fill(caminoMin, -1);

        ColaPrioridad<DijkstraPair> cp = new PriorityQColaPrioridad<>();
        cp.insertar(new DijkstraPair(origen, 0));

        distanciaMin[origen] = 0;

        while (!cp.esVacia()) {
            int v = cp.eliminarMin().position;
            if (visitados[v] == 0) {
                visitados[v] = 1;
                ListaConPI<Adyacente> listaW = adyacentesDe(v);
                for (listaW.inicio(); !listaW.esFin(); listaW.siguiente()) {
                    Adyacente w = listaW.recuperar();
                    if (distanciaMin[w.destino] > distanciaMin[v] + w.peso) {
                        distanciaMin[w.destino] = distanciaMin[v] + w.peso;
                        caminoMin[w.destino] = v;
                        cp.insertar(new DijkstraPair(w.destino, distanciaMin[w.destino]));
                    }
                }
            }
        }
    }

    /**Devuelve la distancia minima entre los vertices origen y destino
      * @param origen  Vertice origen
      * @param destino Vertice destino
      * @return Distancia minima desde origen hasta destino
     */
    public double distanciaMinima(int origen, int destino) {
        if (origen == destino) return 0;
        dijkstra(origen);
        return distanciaMin[destino];
    }

    /** Devuelve decodificado en una Lista Con PI el camino minimo con pesos
      * entre los vertices origen y destino de un grafo.
      * @param origen  Vértice origen del camino a decodificar
      * @param destino Vértice destino del camino a decodificar
      * @return ListaConPI<Integer> con los vertices que componen el camino
      * minimo entre origen y destino
      */
    public ListaConPI<Integer> caminoMinimo(int origen, int destino) {
        ListaConPI<Integer> res = new LEGListaConPI<>();
        res.inicio();
        if (origen != destino) {
            dijkstra(origen);
            for (int v = destino; v != origen; v = caminoMin[v]) {
                if (v == -1) return new LEGListaConPI<>();
                res.insertar(v);
                res.inicio();
            }
        }
        res.insertar(origen);
        return res;
    }

	/** Precondition: 0 <= v < numVertices()*/
    public int masLejosDe(int v) {
        dijkstra(v);
        int res = v;
        for (int i = 0; i < distanciaMin.length; i++) {
            if (distanciaMin[i] != INFINITO && distanciaMin[i] > distanciaMin[res]) res = i;
        }
        return res;
    }

    public boolean esConexo() {
        boolean[] connected = new boolean[numVertices()];
        ListaConPI<Integer> lista = new LEGListaConPI<>();
        int verticesConnected = 1;

        connected[0] = true;
        lista.insertar(0);

        while (!lista.esVacia()) {
            lista.inicio();
            int i = lista.recuperar();
            lista.eliminar();

            ListaConPI<Adyacente> listaAdyacentes = adyacentesDe(i);

            for (listaAdyacentes.inicio(); !listaAdyacentes.esFin(); listaAdyacentes.siguiente()) {
                int v = listaAdyacentes.recuperar().destino;
                if (!connected[v]) {
                    connected[v] = true;
                    verticesConnected++;
                    lista.insertar(v);
                }
            }
        }
        return verticesConnected == numVertices();
    }

    public int numIsolated() {
        boolean[] connected = new boolean[numVertices()];
        int count = 0;
        for (int i = 0; i < numVertices(); i++) {
            ListaConPI<Adyacente> lista = adyacentesDe(i);
            if (!lista.esVacia()) {
                if (!connected[i]) {
                    connected[i] = true;
                    count++;
                }
                for (lista.inicio(); !lista.esFin(); lista.siguiente()) {
                    if (!connected[lista.recuperar().destino]) {
                        connected[lista.recuperar().destino] = true;
                        count++;
                    }
                }
            }
        }
        return numVertices() - count;
    }

    public boolean inCycle(int v) {
        boolean[] visited = new boolean[numVertices()];
        return inCycle(v, visited, v);
    }

    private boolean inCycle(int v, boolean[] visited, int origen) {
        ListaConPI<Adyacente> lista = adyacentesDe(v);
        boolean res = false;
        visited[v] = true;
        for (lista.inicio(); !lista.esFin() && !res; lista.siguiente()) {
            int destino = lista.recuperar().destino;
            if (!visited[destino])
                res = inCycle(destino, visited, origen);
            else if (destino == origen) {
                ListaConPI<Adyacente> adyOrigen = adyacentesDe(origen);
                boolean falsePositive = false;
                for (adyOrigen.inicio(); !adyOrigen.esFin() && !falsePositive; adyOrigen.siguiente()) {
                    if (lista.recuperar().equals(adyOrigen.recuperar()))
                        falsePositive = true;
                }
                res = !falsePositive;
            }
        }
        return res;
    }

    public boolean isBridgeEdge(int origin, int dest) throws NoSuchElementException {
        boolean antes = esConexo();
        Adyacente edge = null;
        ListaConPI<Adyacente> lista = adyacentesDe(origin);
        for (lista.inicio(); !lista.esFin() && edge == null; lista.siguiente()) {
            if (lista.recuperar().destino == dest) {
                edge = lista.recuperar();

                lista.eliminar();
            }
        }
        if (edge == null) throw new NoSuchElementException("No such edge");
        boolean despues = esConexo();
        boolean res = antes && !despues;
        insertarArista(origin, edge.destino, edge.peso);
        return res;
    }

    /**
     * Busca si una arista es un puente (si el grafo se desconectaría al eliminarla)
     * @param i Origen de la arista
     * @param j Destino de la arista
     */
    public boolean esAristaPuente(int i, int j) {
        visitados = new int[numVertices()];
        DFSSinIJ(i, i, j);
        return visitados[j] == 0;
    }

    protected void DFSSinIJ(int v, int i, int j) {
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin() && visitados[j] == 0; l.siguiente()) {
            int destino = l.recuperar().destino;
            if (visitados[destino] == 0)
                if (!(v == i && destino == j)) // si no es la arista
                    if (destino == j) visitados[j] = 1;
                    else DFSSinIJ(destino, i, j);
        }
    }
}

class DijkstraPair implements Comparable<DijkstraPair> {
    int position;
    private double cost;

    DijkstraPair(int p) {this(p, 0);}
    DijkstraPair(int p, double c) {position = p; cost = c;}

    public int compareTo(DijkstraPair pair) {
	return (int) Math.signum(cost - pair.cost);
       // double res = cost - pair.cost;
       // if (res == 0) return 0;
       // else if (res > 0) return 1;
       // else return -1;
    }
}
