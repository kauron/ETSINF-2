package estructurasDeDatos;

public class MonticuloBinario<E extends Comparable<E>> {
    private static int INIT_CAPACITY = 50;
    
    private E[] elArray;
    private int talla;
    
    @SuppressWarnings("unchecked")
    public MonticuloBinario() {
        elArray = (E[]) new Comparable[INIT_CAPACITY];
        talla = 0;
    }
    
    public void insertar(E e) {
        if (talla + 1 >= elArray.length) resize();
        elArray[++talla] = e;
        for (int i = talla; i > 1 && elArray[i / 2].compareTo(e) > 0; i /= 2) {
            elArray[i] = elArray[i / 2];
            elArray[i / 2] = e;
        }
    }
    
    /** Inserts maintaining the max element at the position talla
     *  @param e Element to be inserted
     * 
     */ 
    public void insertarCool(E e) {
        if (talla == 0 || e.compareTo(elArray[talla]) >= 0)
            insertar(e);
        else {
            E max = elArray[talla--];
            insertar(e);
            insertar(max);
        }
    }
    
    private void resize() {
        E[] aux = (E[]) new Comparable[INIT_CAPACITY + elArray.length];
        System.arraycopy(elArray, 0, aux, 0, elArray.length);
        elArray = aux;
    }
}