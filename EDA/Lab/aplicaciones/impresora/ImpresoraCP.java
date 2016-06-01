package aplicaciones.impresora;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinarioMaxR0;

/** Clase ImpresoraCola. Representa una impresora que tiene
 * una Cola de Prioridad para gestionar los documentos a imprimir.
 * @author (profesores EDA 15-16)
 * @version (04 2016)
 **/

public class ImpresoraCP implements Impresora {
    private ColaPrioridad<Documento> cp;    // Cola de impresion
    
    /** Crea una nueva impresora */
    public ImpresoraCP() {
        
        // COMPLETAR
        
    }
    
    /** Guarda un trabajo en la cola de impresion
     * @param doc   Documento a imprimir
     */
    public void guardarTrabajo(Documento doc) {
        
        // COMPLETAR
    
    }
    
    /** Consulta si hay trabajos a imprimir en la cola de impresion
     * @return boolean que indica si hay trabajos pendientes
     */
    public boolean hayTrabajos() {
        
        // COMPLETAR
    
    }
    
    /** SII hayTrabajos(): consulta el siguiente trabajo a imprimir
     * @return Siguiente documento a imprimir
     */
    public Documento siguienteTrabajo() {
        
        // COMPLETAR
    
    }
    
    /** SII hayTrabajos(): elimina de la cola de impresion el siguiente
     * trabajo a imprimir.
     * @return Duracion de la impresion del siguiente trabajo (en seg.)
     */
    public int imprimirSiguiente() {
        
        // COMPLETAR
        
    }
}
