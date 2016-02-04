package EDA;

public interface Stack<E> {
    E pop() throws Exception;
    E top() throws Exception;
    void push(E element);
    boolean isEmpty();
}