package librerias.estructurasDeDatos.modelos;

/**
 * Modelo Diccionario SIN claves repetidas, o Map: Busqueda Dinamica 
 * de una Entrada de Clave dada en una Coleccion, para recuperar el 
 * Valor asociado a esta 
 * @param <C>, el tipo de sus claves
 * @param <V>, el tipo de sus valores
 * @version Febrero 2014
 */

public interface Map<C, V> {

    /** inserta la Entrada (c,v) en un Map y devuelve null; si ya
     *  existe una Entrada de Clave c en el Map, devuelve su valor 
     *  asociado y lo substituye por v (o actualiza la Entrada)
     */
    V insertar(C c, V v);
    
    /** elimina la Entrada con Clave c de un Map y devuelve su 
     *  valor asociado, null si no existe una Entrada con dicha clave
     */
    V eliminar(C c);
    
    /** devuelve el valor asociado a la clave c en un Map,
     *  null si no existe una Entrada con dicha clave
     */
    V recuperar(C c);
    
    /** comprueba si un Map esta vacio */
    boolean esVacio();
    
    /** devuelve la talla, o numero de Entradas, de un Map */
    int talla();
    
    /** devuelve una ListaConPI con las talla() Claves de un Map */
    ListaConPI<C> claves();
}

