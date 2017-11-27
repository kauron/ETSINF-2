import librerias.estructurasDeDatos.modelos.ColaPrioridad;

public class MonticuloBinario<E extends Comparable<E>> implements ColaPrioridad<E> {
    private static final int CAPACIDAD_INICIAL = 50;
    protected E[] elArray;
    protected int talla;

    @SuppressWarnings("unchecked")
    public MonticuloBinario() {
        elArray = (E[]) new Comparable[CAPACIDAD_INICIAL];
    }

    @Override
    public void insertar(E e) {
        if (talla + 1 >= elArray.length) duplicateSize();
        elArray[++talla] = e;
        for (int i = talla; elArray[i/2].compareTo(elArray[i]) <= 0 && i > 1; i /= 2) {
            elArray[i] = elArray[i/2];
            elArray[i/2] = e;
        }
    }

    @SuppressWarnings("unchecked")
    private void duplicateSize() {
        E[] aux = (E[]) new Comparable[elArray.length * 2];
        System.arraycopy(elArray, 1, aux, 1, talla);
        elArray = aux;
    }

    @Override
    public E eliminarMin() {
        return null;
    }

    @Override
    public E recuperarMin() {
        return elArray[1];
    }

    @Override
    public boolean esVacia() {
        return talla == 0;
    }

}
