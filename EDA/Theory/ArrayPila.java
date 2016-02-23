package EDA;

import java.lang.reflect.Array;

public class ArrayPila<E> implements Pila<E> {
    private static final int INIT_SIZE = 10;
    private E[] stack;
    private int size;

    public ArrayPila(Class<E> c) {
        @SuppressWarnings("unchecked")
        final E[] a = (E[]) Array.newInstance(c, INIT_SIZE);
        stack = a;
        size = 0;
    }

    public boolean esVacía() {return size == 0;}
    public int size() {return size;}

    public E top() throws Exception{
        if (esVacía()) throw new Exception("Empty Pila");
        return stack[size - 1];
    }

    public void push(E e) {
        if (size == stack.length) resize();
        stack[size++] = e;
    }

    public E pop() throws Exception {
        if (esVacía()) throw new Exception("Empty Pila");
        return stack[--size];
    }

    private void resize() {
        @SuppressWarnings("unchecked")
        final E[] aux = (E[]) Array.newInstance(stack[0].getClass(), INIT_SIZE + size);
        System.arraycopy(stack, 0, aux, 0, stack.length);
        stack = aux;
    }

    public String toString() {
        String s = "| ";
        for (int i = 0; i < size; i++) {
            s += stack[i].toString();
            if (i + 1 < size) s += " -> ";
        }
        return s;
    }
}