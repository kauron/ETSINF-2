package librerias.estructurasDeDatos.lineales;

/**
 * Generic stack implemented by means of a double linked list.
 * Previous advances towards the top element
 * Next advances towards the end (bottom) of the stack
 */
public class LinkedListPila<E> implements Pila<E> {
    protected Nodo<E> top;

    public LinkedListPila() {top = null;}

    @Override
    public E pop() throws Exception {
        if (esVacía()) throw new Exception ("Empty Pila");
        E e = top.dato;
        top = top.next;
        if (!esVacía())
            top.previous = null;
        return e;
    }

    @Override
    public E top() throws Exception {
        if (esVacía()) throw new Exception("Empty Pila");
        return top.dato;
    }

    @Override
    public void push(E element) {
        if (!esVacía()) {
            top.previous = new Nodo<>(top, element);
            top = top.previous;
        } else {
            top = new Nodo<>(element);
        }
    }

    @Override
    public boolean esVacía() {
        return top == null;
    }

    @Override
    public String toString() {
        String s = "| ";
        for (Nodo<E> n = top; n != null; n = n.next) {
            s += n.dato.toString();
            if (n.next != null) s += " -> ";
        }
        return s;
    }
}

