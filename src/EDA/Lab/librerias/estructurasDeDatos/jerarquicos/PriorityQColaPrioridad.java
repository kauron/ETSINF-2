package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import java.util.PriorityQueue;

/** Implementacion de una Cola de Prioridad mediante una PriorityQueue (Heap) 
  *
  * @version Febrero 2014 
  * @param <E> tipo de datos de la estructura, debe implementar Comparable<E>
  */

public class PriorityQColaPrioridad<E extends Comparable<E>> 
    /* COMPLETAR con extends y con implements)*/
 
{
    
    /** crea una Cola de Prioridad (CP) vacia */
    public PriorityQColaPrioridad() { /* COMPLETAR */ }
    
    /** atendiendo a su prioridad, inserta el Elemento e en una Cola 
     * de Prioridad (CP)
     *  @param e Elemento a agnadir a una Cola de Prioridad
     */
    public void insertar(E e) { /* COMPLETAR */ }
    
    /** SII !esVacia(): obtiene el Elemento con maxima prioridad de una CP 
     * @return E Elemento con maxima prioridad de una CP
     */
    public E recuperarMin() { 
	    E res = null;
		/* COMPLETAR */ 
		return res; 
    }
    
     /** SII !esVacia(): obtiene y elimina el Elemento con maxima 
      * prioridad de una CP 
     *  @return E Elemento con maxima prioridad de una CP
     */
    public E eliminarMin() { 
	    E res = null;
		/* COMPLETAR */ 
		return res; 
    }
    
    /** comprueba si una Cola de Prioridad esta vacia
      * @return true si una CP esta vacia y false en caso contrario
      */
    public boolean esVacia() { 
	    boolean res = false;
		/* COMPLETAR */ 
		return res; 
    }
}