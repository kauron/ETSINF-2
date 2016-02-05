package EDA;

public class LinkedListStack<E> implements Stack<E> {
    protected Node<E> top;

    public LinkedListStack() {top = null;}

    @Override
    public E pop() throws Exception {
        if (isEmpty()) throw new Exception ("Empty Stack");
        E e = top.element;
        top = top.previous;
        if (!isEmpty())
            top.next = null;
        return e;
    }

    @Override
    public E top() throws Exception {
        if (isEmpty()) throw new Exception("Empty Stack");
        return top.element;
    }

    @Override
    public void push(E element) {
        if (!isEmpty()) {
            top.next = new Node<>(top, element);
            top = top.next;
        } else {
            top = new Node<>(element);
        }
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        String s = "| ";
        for (Node<E> n = top; n != null; n = n.previous) {
            s += n.element.toString();
            if (n.previous != null) s += " -> ";
        }
        return s;
    }
}

