package aplicaciones.impresora;

/** Interfaz Impresora. Especifica los metodos que
 * ha de implementar una impresora.
 * @author (profesores EDA 15-16)
 * @version (04 2016)
 **/

public interface Impresora {
    // Paginas por minuto que puede imprimir la impresora
    int PAGINAS_POR_MINUTO = 30;
    
    /** Guarda un trabajo en la cola de impresion
     * @param doc   Documento a imprimir
     */
    void guardarTrabajo(Documento doc);
    
    /** Consulta si hay trabajos a imprimir en la cola de impresion
     * @return boolean que indica si hay trabajos pendientes
     */
    boolean hayTrabajos();
    
    /** SII hayTrabajos(): consulta el siguiente trabajo a imprimir
     * @return Siguiente documento a imprimir
     */
    Documento siguienteTrabajo();
    
    /** SII hayTrabajos(): elimina de la cola de impresion el siguiente
     * trabajo a imprimir.
     * @return Duracion de la impresion del siguiente trabajo (en seg.)
     */
    int imprimirSiguiente();
}
