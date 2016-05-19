package librerias.estructurasDeDatos.lineales;

// comprueba que lo has puesto en el lugar correcto
// package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;

/** Implementacion de una Cola de Prioridad mediante una Lista Con PI
  *
  * @version Febrero 2014
  */

public class LPIColaPrioridad<E extends Comparable<E>>
    extends LEGListaConPI<E>
    implements ColaPrioridad<E> {

    /** crea una Cola de Prioridad (CP) vacia */
    public LPIColaPrioridad() { super(); }

    /** atendiendo a su prioridad, inserta el elemento "e" en una
     * Cola de Prioridad (CP).
	 *
     *  @param e Elemento a agnadir a una Cola de Prioridad
     */
    public void insertar(E e) {
        inicio();
        while (!esFin() && recuperar().compareTo(e) <= 0) siguiente();
        super.insertar(e);
    }

    /** SII !esVacia(): obtiene el elemento con maxima prioridad de
	 * una CP.
	 *
     * @return E Elemento con maxima prioridad de una CP.
     */
    public E recuperarMin() {
        inicio();
        return recuperar();
    }

    /** SII !esVacia(): obtiene y elimina el elemento con maxima
	 *  prioridad de una CP.
     *  @return E Elemento con maxima prioridad de una CP
     */
    public E eliminarMin() {
        inicio();
	    E res = recuperar();
        eliminar();
		return res;
    }

    /** Comprueba si una Cola de Prioridad esta vacia.
      * @return true si una CP esta vacia y false en caso contrario.
      */
    public boolean esVacia() { return ( super.esVacia() ); }
}
