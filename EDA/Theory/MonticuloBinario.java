import estructurasDeDatos.modelos.ColaPrioridad;

public class MonticuloBinario<E extends Comparable<E>> implements ColaPrioridad<E> {
    protected static final int CAPACIDAD_INICIAL = 50;
    protected E[] elArray;
    protected int talla;

    @SupressWarnings("unchecked")
    public MonticuloBinario() {
        elArray = (E[]) new Comparable[CAPACIDAD_INICIAL];
    }

    public void insertar(E e) {
        if (talla >= elArray.length) resize();
        elArray[++talla] = e;
        for (int i = talla; elArray[i/2].compareTo(elArray[i]) <= 0 && i > 1; i /= 2) {
            elArray[i] = elArray[i/2];
            elArray[i/2] = e;
        }
    }

}
