package EDA;

/**
 * Generic stack implemented by means of a double linked list.
 * Previous advances towards the top element
 * Next advances towards the end (bottom) of the stack
 */
public class LinkedListStack<E> implements Stack<E> {
    protected Node<E> top;

    public LinkedListStack() {top = null;}

    @Override
    public E pop() throws Exception {
        if (isEmpty()) throw new Exception ("Empty Stack");
        E e = top.element;
        top = top.next;
        if (!isEmpty())
            top.previous = null;
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
            top.previous = new Node<>(top, element);
            top = top.previous;
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
        for (Node<E> n = top; n != null; n = n.next) {
            s += n.element.toString();
            if (n.next != null) s += " -> ";
        }
        return s;
    }
}

