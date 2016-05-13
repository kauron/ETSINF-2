package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.estructurasDeDatos.jerarquicos.PriorityQColaPrioridad;
import java.util.ArrayList;

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
    }

    /**Devuelve la distancia minima entre los vertices origen y destino
      * @param origen  Vertice origen
      * @param destino Vertice destino
      * @return Distancia minima desde origen hasta destino
     */
    public double distanciaMinima(int vOrigen, int vDestino) {
        dijkstra(vOrigen);
        return distanciaMin[vDestino];
    }

    /** Devuelve decodificado en una Lista Con PI el camino minimo con pesos
      * entre los vertices origen y destino de un grafo.
      * @param origen  Vertice origen del camino a decodificar
      * @param destino Vertice destino del camino a decodificar
      * @return ListaConPI<Integer> con los vertices que componen el camino
      * minimo entre origen y destino
      */
    public ListaConPI<Integer> caminoMinimo(int origen, int destino) {
    }
}
