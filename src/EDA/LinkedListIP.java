package EDA;

public class LinkedListIP<E> implements InterestPointList<E> {
    protected Node<E> first = null, pointer = null;
    protected int size = 0;

    @Override
    public void insert(E e) {
        if (isEmpty())
            first = new Node<>(e);
        else if (isEnd())
        {

        }
        else
            pointer.setNext(new Node<>(e, pointer.getNext()));
        size++;
    }

    @Override
    public void erase() throws Exception {
        if (isEnd()) throw new Exception("Badly placed pointer");

    }

    @Override
    public E recover() {
        return null;
    }

    @Override
    public void begin() {

    }

    @Override
    public void end() {

    }

    @Override
    public void next() {

    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
