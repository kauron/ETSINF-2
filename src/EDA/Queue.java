package EDA;

public interface Queue<E> {
    void queue(E element);
    E dequeue() throws Exception;
    boolean isEmpty();
    String toString();
}
