package EDA;

import java.lang.reflect.Array;

public class ArrayCola<E> implements Cola<E> {
    protected static final int INIT_SIZE = 10;
    protected E[] queue;
    protected int size;
    protected int first, last; //first occupied, last occupied

    public ArrayCola(Class<E> c) {
        @SuppressWarnings("unchecked")
        final E[] a = (E[]) Array.newInstance(c, INIT_SIZE);
        queue = a;
        size = 0;
        first = -1;
        last = -1;
    }

    @Override
    public void queue(E element) {
        if (first == -1) first = 0;
        if (size == queue.length) resize();
        last = (last + 1) % queue.length;
        queue[last] = element;
        size++;
    }

    @Override
    public E dequeue() throws Exception {
        if (isEmpty()) throw new Exception("Empty queue");
        size--;
        E e = queue[first];
        first = (first + 1) % queue.length;
        if (size == 0) last = first = -1;
        return e;
    }

    @Override
    public E first() throws Exception {
        if (isEmpty()) throw new Exception("Empty Cola");
        return queue[first];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected void resize() {
        @SuppressWarnings("unchecked")
        final E[] aux = (E[]) Array.newInstance(queue[0].getClass(), INIT_SIZE + size);
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
