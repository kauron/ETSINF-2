package aplicaciones.municipios;

import librerias.estructurasDeDatos.grafos.GrafoDirigido;
import librerias.estructurasDeDatos.grafos.Adyacente;
import java.util.ArrayList;
import java.util.Scanner;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.deDispersion.TablaHash;
import java.io.File;
import java.io.FileNotFoundException;

/**Gestor del grafo de municipios: esta clase dispone internamente de un Grafo
 * de Municipios y se encarga de gestionar todas las operaciones habituales del
 * mismo: su inicializacion desde el fichero de municipios y distancias y la
 * busqueda de caminos minimos
 *
 * @author (profesores EDA)
 * @version (Curso 2015/16)
 */
public class GestorGrafoMunicipios {

    public final static String FICH_MUNICIPIOS = "aplicaciones" + File.separator
        + "municipios" + File.separator + "municipios.txt";

    private static final int NUM_MUNICIPIOS = 5000;
    // Numero estimado de municipios

    private static final String FICH_DISTS = "aplicaciones" + File.separator
        + "municipios" + File.separator + "distancias.txt";

    private static final String NO_ACC_MSG = "El fichero de municipios no es "
        + "accesible para lectura, compruebe su correcta ubicaci\u00f3n";
    private static final String NO_FOR_MSG = "Formato no v\u00e1lido en "
        + "la l\u00ednea: ";
    private static final String NO_FDIS_MSG = "Fichero de distancias "
        + "no encontrado";

    private GrafoDirigido grafo;

    // Etiquetado de los vertices
    private Municipio[] munis;
    private Map<Municipio, Integer> dicMunis;

    /**Constructor: crea el grafo y lo inicializa con los datos contenidos en
     * los ficheros de municipios y distancias
     */
    public GestorGrafoMunicipios() {
        ArrayList<Municipio> munisAL = new ArrayList<Municipio>(NUM_MUNICIPIOS);
        try {
            Scanner fent = new Scanner(new File(FICH_MUNICIPIOS), "UTF-8");
            while (fent.hasNext()) {
                String linea = fent.nextLine();
                String[] lA = linea.split(";");
                Municipio m = new Municipio(lA[0], Integer.parseInt(lA[1]),
                    Double.parseDouble(lA[2]), Integer.parseInt(lA[3]),
                    Integer.parseInt(lA[4]));
                munisAL.add(m);
            }
            fent.close();
            munis = munisAL.toArray(new Municipio[munisAL.size()]);
            dicMunis = new TablaHash<Municipio, Integer>(munis.length);
            for (int i = 0; i < munis.length; i++) {
                dicMunis.insertar(munis[i], i);
            }
            grafo = new GrafoDirigido(munis.length);
            cargarAristas();
        } catch (java.io.IOException eChecked) {
            System.out.println(NO_ACC_MSG);
        }
    }

    // Inserta las aristas contenidas en el fichero de distancias en el grafo
    private void cargarAristas() {
        try {
            Scanner f = new Scanner(new File(FICH_DISTS), "UTF-8");
            while (f.hasNext()) {
                String linea = f.nextLine();
                String[] datosCarretera = linea.split(";");
                if (datosCarretera.length != 3) {
                    System.out.println(NO_FOR_MSG  + linea);
                    break;
                }
                String orig = datosCarretera[0].trim().toLowerCase();
                String dst = datosCarretera[1].trim().toLowerCase();
                double distancia = Double.parseDouble(datosCarretera[2]);
                int origen = obtenerCodigo(new Municipio(orig)),
                    destino = obtenerCodigo(new Municipio(dst));
                grafo.insertarArista(origen, destino, distancia);
                grafo.insertarArista(destino, origen, distancia);
            }
        } catch (FileNotFoundException e) {
            System.err.println(NO_FDIS_MSG);
        }
    }

    /**Recupera el codigo de un vertice a partir de su etiqueta.
     * Devuelve -1 si no existe un vertice con dicha etiqueta
     */
    public int obtenerCodigo(Municipio etiqueta) {
        Integer codigo = dicMunis.recuperar(etiqueta);
        if (codigo == null) { return -1; }
        return codigo.intValue();
    }

    /**Consulta el n�mero de municipios que contiene el grafo
     * @return     N�mero de municipios del grafo
     */
    public int numMunicipios() { return grafo.numVertices(); }

    /**Recupera los datos de un municipio del grafo
     * @param   indice  �ndice interno del v�rtice
     * @return          Municipio asociado a dicho v�rtice
     */
    public Municipio getMunicipio(int indice) { return munis[indice]; }

    /**Devuelve el numero de aristas recuperadas del fichero de distancias
     * En el grafo el numero de aristas sera el doble pues se insertan
     * las aristas simetricas
     * @return     Numero de aristas
     */
    public int numAristas() { return grafo.numAristas(); }

    /**Devuelve la lista de municipios adyacentes
     * @param  indice del municipio
     * @return ListaConPI con los municipios adyacentes al municipio �ndice
     */
    public ListaConPI<Adyacente> adyacentesDe(int indice) {
        return grafo.adyacentesDe(indice);
    }

    /**Comprueba si existe un municipio en el grafo
     * @param  m   Municipio a buscar
     * @return True si dicho municipio est� en el grafo
     */
    public boolean existeMunicipio(Municipio m) {
        return obtenerCodigo(m) != -1;
    }

    /** Recupera el coste de una arista
     * @param  m1   Municipio origen de la arista
     * @param  m2   Municipio destino de la arista
     * @return Coste de la arista (o -1 si dicha arista no existe en el grafo)
     */
    public double distancia(Municipio m1, Municipio m2) {
        return grafo.pesoArista(obtenerCodigo(m1), obtenerCodigo(m2));
    }

    /**Calcula el camino minimo entre dos municipios
     * @param   mOrig   Municipio origen del camino
     * @param   mDst    Municipio destino del camino
     * @return  Camino minimo en forma de ListaConPI de municipios
     */
    public ListaConPI<Municipio> caminoMinimo(Municipio mOrig, Municipio mDst) {
        ListaConPI<Integer> lista = grafo.caminoMinimo(obtenerCodigo(mOrig), obtenerCodigo(mDst));
        ListaConPI<Municipio> res = new LEGListaConPI<>();
        for (lista.inicio(); !lista.esFin(); lista.siguiente()) {
            res.insertar(getMunicipio(lista.recuperar()));
        }
        return res;
    }
}
