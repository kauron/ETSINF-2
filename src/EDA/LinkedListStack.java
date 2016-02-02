package EDA;

public class LinkedListStack<T> implements Stack<T> {
    protected Node<T> top;

    public LinkedListStack() {top = null;}

    @Override
    public T pop() throws Exception {
        if (isEmpty()) throw new Exception ("Empty Stack");
        T t = top.element;
        top = top.previous;
        if (!isEmpty())
            top.next = null;
        return t;
    }

    @Override
    public T top() throws Exception {
        if (isEmpty()) throw new Exception("Empty Stack");
        return top.element;
    }

    @Override
    public void push(T element) {
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

class Node<T> {
    T element;
    Node<T> previous, next;

    public Node(T element) {this(element, null);}
    public Node(Node<T> previous, T element) {this(previous, element, null);}
    public Node(T element, Node<T> next) {this(null, element, next);}
    public Node(Node<T> previous, T element, Node<T> next) {
        this.previous = previous;
        this.element = element;
        this.next = next;
    }
}