package aplicaciones.impresora;

import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.lineales.ArrayCola;

/** Clase ImpresoraCola. Representa una impresora que tiene
 * una Cola para gestionar los documentos a imprimir.
 * @author (profesores EDA 15-16)
 * @version (04 2016)
 **/

public class ImpresoraCola implements Impresora {
    private Cola<Documento> cola;   // Cola de impresion
    
    /** Crea una nueva impresora */
    public ImpresoraCola() {
        cola = new ArrayCola<Documento>();
    }
    
    /** Guarda un trabajo en la cola de impresion
     * @param doc   Documento a imprimir
     */
    public void guardarTrabajo(Documento doc) {
        cola.encolar(doc);
    }
    
    /** Consulta si hay trabajos a imprimir en la cola de impresion
     * @return boolean que indica si hay trabajos pendientes
     */
    public boolean hayTrabajos() {
        return !cola.esVacia();
    }
    
    /** SII hayTrabajos(): consulta el siguiente trabajo a imprimir
     * @return Siguiente documento a imprimir
     */
    public Documento siguienteTrabajo() {
        return cola.primero();
    }
    
    /** SII hayTrabajos(): elimina de la cola de impresion el siguiente
     * trabajo a imprimir.
     * @return Duracion de la impresion del siguiente trabajo (en seg.)
     */
    public int imprimirSiguiente() {
        Documento doc = cola.desencolar();
        int tiempoImpresion = 60 * doc.getNumPaginas() / PAGINAS_POR_MINUTO;
        return tiempoImpresion;
    }
}
