package EDA;

public interface Stack<T> {
    T pop() throws Exception;
    T top() throws Exception;
    void push(T element);
    boolean isEmpty();
}