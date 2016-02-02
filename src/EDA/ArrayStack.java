package EDA;

import java.lang.reflect.Array;

public class ArrayStack<T> implements Stack<T>{
    private static final int MIN_SIZE = 10;
    private T[] stack;
    private int size;

    public ArrayStack(Class<T> c) {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(c, MIN_SIZE);
        stack = a;
        size = 0;
    }

    public boolean isEmpty() {return size == 0;}
    public int size() {return size;}

    public T top() throws Exception{
        if (isEmpty()) throw new Exception("Empty Stack");
        return stack[size - 1];
    }

    public void push(T t) {
        if (size == stack.length) resize();
        stack[size++] = t;
    }

    public T pop() throws Exception {
        if (isEmpty()) throw new Exception("Empty Stack");
        return stack[--size];
    }

    private void resize() {
        @SuppressWarnings("unchecked")
        final T[] aux = (T[]) Array.newInstance(stack[0].getClass(), MIN_SIZE + size);
        System.arraycopy(stack, 0, aux, 0, stack.length);
        stack = aux;
    }
}