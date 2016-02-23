package LTP.Lab2.librerias.modelos;
/**
 * Define el TAD de una cola
 * @author (Professors LTPP) 
 * @version (curs 2015-16)
 */
public interface Cola<T> {
    // metodos Modificadores del estado de una Cola:
    /** inserta el Elemento en una Cola situandolo al final
       @param e elemento a insertar**/
    void encolar(T e);

    /** Consulta y extrae el primer elemento, solo si la cola no esta vacia
       @return el elemento desencolado**/
    T desencolar();


    // metodos Consultores del estado de una Cola

    /** Devuelve la cantidad de elementos de la cola
      @return cantidad de elementos de la cola**/
    int talla();

    /** Solo si la cola no esta vacia, consulta el primer elemento en cabeza,
     * (el primero en orden de insercion)
     * @return el primer elemento de la cola**/
    T primero();

    /** Comprueba si una Cola esta vacia
     * @return true si y solo si la cola esta vacia**/
    boolean esVacia();
}
