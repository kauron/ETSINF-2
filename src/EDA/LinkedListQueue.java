package EDA;

public class LinkedListQueue<E> implements Queue<E> {
    protected Node<E> first, last;

    public LinkedListQueue() {
        first = last = null;
    }

    @Override
    public void queue(E element) {
        if (isEmpty()) first = last = new Node<>(element);
        else last = new Node<>(last, element);
    }

    @Override
    public E dequeue() throws Exception {
        if (isEmpty()) throw new Exception("Empty Queue");
        E e = first.element;
        first = first.next;
        return e;
    }

    @Override
    public E first() throws Exception {
        if (isEmpty()) throw new Exception("Empty Queue");
        return first.element;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        String s = "[ ";
        for (Node<E> n = first; n != null; n = n.next) {
            s += n.element.toString();
            if (n.next != null) s += ", ";
        }
        return s;
    }
}
