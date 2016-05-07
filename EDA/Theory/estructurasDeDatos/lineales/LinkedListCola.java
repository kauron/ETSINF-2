package estructurasDeDatos.lineales;

import estructurasDeDatos.modelos.Cola;

public class LinkedListCola<E> implements Cola<E> {
    protected Nodo<E> first, last;

    public LinkedListCola() {
        first = last = null;
    }

    @Override
    public void encolar(E element) {
        if (esVacia()) first = last = new Nodo<>(element);
        else {
            last.next = new Nodo<>(last, element);
            last = last.next;
        }
    }

    @Override
    public E desencolar() throws Exception {
        if (esVacia()) throw new Exception("Empty Cola");
        E e = first.dato;
        first = first.next;
        return e;
    }

    @Override
    public E primero() throws Exception {
        if (esVacia()) throw new Exception("Empty Cola");
        return first.dato;
    }

    @Override
    public boolean esVacia() {
        return first == null;
    }

    @Override
    public String toString() {
        String s = "[ ";
        for (Nodo<E> n = first; n != null; n = n.next) {
            s += n.dato.toString();
            if (n.next != null) s += ", ";
        }
        return s;
    }
}
