package EDA;

public class ListaEnlazadaConPI<E> implements ListaConPIExt<E> {
    protected Nodo<E> first = null, last = null, pointer = null;
    protected int size = 0;

    @Override
    public void insertar(E e) {
        if (esVacia()) {
            first = last = new Nodo<>(e);
            size++;
            return;
        } else if (esFin())
            pointer = last;

        pointer.setNext(new Nodo<>(e, pointer.getNext()));
        if (pointer == last) last = pointer.getNext();
        size++;
    }

    @Override
    public void eliminar() throws Exception {
        if (esFin()) throw new Exception("Badly placed pointer");
        
    }

    @Override
    public E recuperar() {
        return null;
    }

    @Override
    public void inicio() {

    }

    @Override
    public void fin() {

    }

    @Override
    public void siguiente() {

    }

    @Override
    public boolean esFin() {
        return false;
    }

    @Override
    public boolean esVacia() {
        return false;
    }

    @Override
    public int talla() {
        return 0;
    }

    @Override
    public void search(E e) {

    }

    @Override
    public void vaciar() {

    }

    @Override
    public void reverse() {

    }

    @Override
    public int deleteAll(E x) {
        inicio();
        int c = 0;
        while(!esFin()) {
            if (recuperar().equals(x)) {
                try {
                    eliminar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                c++;
            }
            siguiente();
        }
        return c;
    }
}
