package estructurasDeDatos.lineales;

import estructurasDeDatos.modelos.Cola;

import java.util.NoSuchElementException;

public class ArrayCola<E> implements Cola<E> {
    private static final int INIT_SIZE = 10;
    private E[] queue;
    private int size;
    private int first, last; //first occupied, last occupied

    public ArrayCola() {
        @SuppressWarnings("unchecked")
        final E[] a = (E[]) new Object[INIT_SIZE];
        queue = a;
        size = 0;
        first = -1;
        last = -1;
    }

    @Override
    public void encolar(E element) {
        if (first == -1) first = 0;
        if (size == queue.length) resize();
        last = (last + 1) % queue.length;
        queue[last] = element;
        size++;
    }

    @Override
    public E desencolar() throws NoSuchElementException {
        if (esVacia()) throw new NoSuchElementException("Empty queue");
        size--;
        E e = queue[first];
        first = (first + 1) % queue.length;
        if (size == 0) last = first = -1;
        return e;
    }

    @Override
    public E primero() throws NoSuchElementException {
        if (esVacia()) throw new NoSuchElementException("Empty Cola");
        return queue[first];
    }

    @Override
    public boolean esVacia() {
        return size == 0;
    }

    private void resize() {
        @SuppressWarnings("unchecked")
        final E[] aux = (E[]) new Object[INIT_SIZE + size];
        if (first < last) {
            System.arraycopy(queue, first, aux, 0, size);
        } else {
            System.arraycopy(queue, first, aux, 0, queue.length - first);
            System.arraycopy(queue, 0, aux, queue.length - first, last + 1);
        }
        queue = aux;
    }

    @Override
    public String toString() {
        String s = "[ ";
        for (int i = first; i - first < size; i = (i + 1) % queue.length) {
            s += queue[i].toString();
            if (i + 1 < size) s += ", ";
        }
        return s + " ]";
    }
}
