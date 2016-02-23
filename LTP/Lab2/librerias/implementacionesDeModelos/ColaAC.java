package LTP.Lab2.librerias.implementacionesDeModelos;

/**
 *TAD de una cola implementado con un array circular de amplitud variable
 * 
 * @author (Professors LTPP) 
 * @version (curs 2015-16)
 */

import LTP.Lab2.librerias.modelos.Cola;

import java.util.NoSuchElementException;

public class ColaAC <T> implements Cola<T> {
    static final int MAX = 35;

    T[] elArray;
    int primero, ultimo;
    int talla;

    /**Constructor de Cola */
    //El compilador nos avisa (warning) de que el tipo puro correspondiente a T en (1) se aplicara
    //en tiempo de ejecucion. Con esta directiva le decimos que no nos muestre el aviso ya que la coercion es segura.
    @SuppressWarnings({"unchecked"})
    public ColaAC () {
        elArray = (T[]) (new Object [MAX]); //(1)
        primero = 0;
        ultimo = -1;
        talla = 0;
    }


    /** Metodo privado para ampliar el array si hace falta**/
    @SuppressWarnings({"unchecked"})
    private void ampliarElArray(){

        T[]  arrayAux = (T[]) (new Object [elArray.length*2]);
        int i = 0,
        tallaAux=talla;
        while(!this.esVacia())
          try{
              arrayAux [i++] = this.desencolar();
          }catch(Exception e){
              System.out.println("Error al intentar ampliar una cola");
          }
        elArray = arrayAux;
        this.primero = 0;
        this.ultimo = tallaAux-1;
        this.talla = tallaAux;

    }
  
  
    // Implementacion de las operaciones del TAD definido en la interfaz Cola <T>:
    // Metodos modificadores del estado de una cola:
  
    /** Inserta el Elemento e en una cola situandolo al final **/
    public void encolar(T e){
        if (talla > MAX)
            ampliarElArray();
        ultimo = incrementar(ultimo);
        elArray[ultimo] = e;
        talla++;
    }

    /** Consulta y extrae el primer elemento, solo si la cola no esta vacia.**/
    public T desencolar(){
        if (esVacia()) throw new NoSuchElementException("There are no elements");
        T aux = elArray[primero];
        elArray[primero] = null;
        primero = incrementar(primero);
        talla--;
        return aux;
    }
  
    // Metodos consultores del estado de la cola
    /** Devuelve la cantidad de elementos  de la cola **/
    public int talla(){
        return talla;
    }
  
    /** Solo si la cola no esta vacia, consulta el primer elemento en cabeza,
     * (el primero en orden de insercion) **/
    public T primero() {
        if (esVacia()) throw new NoSuchElementException("There are no elements");
        return elArray[primero];
    }
  
    /** Comprueba si una cola esta vacia **/
    public boolean esVacia(){
        return talla == 0;
    }

    /** Devuelve la siguiente posicion ocupada del array modulo MAX.
     * Esta instruccion puede sustituirse por (i+1)%elArray.length**/
    private int incrementar (int i) {
        return (i + 1) % elArray.length;
    }

    /** Devuelve el contenido de la cola con el formato
     * &lt;-elem0&lt;-elem1&lt;-elem2&lt;-...&lt;-elemN&lt;- donde N = talla()-1
     * Cada elem se devuelve con el formato que este definido para su tipo
     **/
    public String toString (){
        String s = "";
        for (int i = primero, c = 0; c < talla; i = incrementar(i), c++)
            s += "<-" + elArray[i];
        return s;
    }
}
