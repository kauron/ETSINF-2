package EDA;

public interface Queue<E> {
    void queue(E element);
    E dequeue() throws Exception;
    E first() throws Exception;
    boolean isEmpty();
}
