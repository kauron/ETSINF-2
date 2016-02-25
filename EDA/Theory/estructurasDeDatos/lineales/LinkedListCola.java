package estructurasDeDatos.lineales;

import estructurasDeDatos.modelos.Cola;

public class LinkedListCola<E> implements Cola<E> {
    protected Nodo<E> first, last;

    public LinkedListCola() {
        first = last = null;
    }

    @Override
    public void queue(E element) {
        if (isEmpty()) first = last = new Nodo<>(element);
        else {
            last.next = new Nodo<>(last, element);
            last = last.next;
        }
    }

    @Override
    public E dequeue() throws Exception {
        if (isEmpty()) throw new Exception("Empty Cola");
        E e = first.dato;
        first = first.next;
        return e;
    }

    @Override
    public E first() throws Exception {
        if (isEmpty()) throw new Exception("Empty Cola");
        return first.dato;
    }

    @Override
    public boolean isEmpty() {
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
