package librerias.estructurasDeDatos.jerarquicos;

/** Clase friendly NodoABB<E>: representa un Nodo de un ABB, por lo que
 *  TIENE UN: 
 *  1.- E dato, que representa el Elemento que ocupa un Nodo de un ABB 
 *  2.- NodoABB<E> izq, un enlace al Hijo Izquierdo de un Nodo de un ABB
 *  3.- NodoABB<E> der, un enlace al Hijo Derecho de un Nodo de un ABB
 *  4.- talla, un entero que representa el tamanyo de un Nodo de un ABB
 *      Un ABB que tiene Nodos con este atributo se denomina ABB con Rango
 *      
 *  @param <E>, el tipo de los datos del ABB
 *  @version (Abril 2016)
 **/
class NodoABB<E> {
    // atributos
    E dato;
    int talla;
    NodoABB<E> izq;
    NodoABB<E> der;
	 
	/** Constructor de un nodo sin hijos
      * @param  valor  Dato a almacenar en el nodo
     */
    public NodoABB(E valor) {
	   this(valor, null, null);
    }
	 
	/**  Constructor de un nodo con un hijo izquierdo y derecho dado
      * @param  valor Dato a almacenar en el nodo
      * @param  hizq   Hijo izquierdo del nodo
      * @param  hder   Hijo derecho del nodo
     */
    public NodoABB(E valor, NodoABB<E> hizq, NodoABB<E> hder) {
        this.dato = valor;
        this.izq = hizq;
        this.der = hder;
        this.talla = 1;
        if (hizq != null) { this.talla += hizq.talla; }
        if (hder != null) { this.talla += hder.talla; }
    }
}
