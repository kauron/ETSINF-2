package librerias.estructurasDeDatos.modelos;

// comprueba que lo has puesto en el lugar correcto
// package librerias.estructurasDeDatos.modelos;

/**
 * Modelo de una Lista Con Punto de Interes, o de Acceso Secuencial 
 * a los Elementos de una Coleccion
 *  
 * @version Febrero 2014
 * @param <E> tipo de datos de la estructura
 */

public interface ListaConPI<E> {
    

// metodos Modificadores del estado de la Lista Con PI:
    /** inserta e en una Lista antes del Elemento que ocupa su PI,
    * que permanece inalterado **/
    void insertar(E e);
     /** SII !esFin(): elimina de una Lista el Elemento que ocupa su PI,
    * que permanece inalterado **/
    void eliminar();
    
// metodos Modificadores del estado del PI de la Lista:
    /** situa el PI de una Lista en su inicio */
    void inicio();
    /** SII !esFin(): avanza el PI de una Lista */
    void siguiente();
    /** situa el PI de una Lista en su fin */
    void fin();
   
// metodos Consultores del estado de la Lista Con PI:
    /** SII !esFin(): obtiene el Elemento que ocupa el PI de una Lista */
    E recuperar();
    /** comprueba si el PI de una Lista esta en su fin */
    boolean esFin();
    /** comprueba si una Lista Con PI esta vacia **/
    boolean esVacia();
    /** devuelve la talla de una Lista, o su numero de elementos **/
    int talla();
}