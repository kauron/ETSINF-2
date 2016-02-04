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
}

class Node<E> {
    E element;
    Node<E> previous, next;

    public Node(E element) {this(element, null);}
    public Node(Node<E> previous, E element) {this(previous, element, null);}
    public Node(E element, Node<E> next) {this(null, element, next);}
    public Node(Node<E> previous, E element, Node<E> next) {
        this.previous = previous;
        this.element = element;
        this.next = next;
    }
}