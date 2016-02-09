package EDA;

public class Node<E> {
    protected E element;
    protected Node<E> previous, next;

    public Node(E element) {this(element, null);}
    public Node(Node<E> previous, E element) {this(previous, element, null);}
    public Node(E element, Node<E> next) {this(null, element, next);}
    public Node(Node<E> previous, E element, Node<E> next) {
        this.previous = previous;
        this.element = element;
        this.next = next;
    }

    public E getElement() {return element;}
    public void setElement(E element) {this.element = element;}

    public Node<E> getNext() {return next;}
    public void setNext(Node<E> next) {this.next = next;}

    public Node<E> getPrevious() {return previous;}
    public void setPrevious(Node<E> previous) {this.previous = previous;}
}
