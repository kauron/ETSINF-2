package EDA;

public interface InterestPointList<E> {
    void insert(E e);
    void erase() throws Exception;
    E recover() throws Exception;

    void begin();
    void end();
    void next();

    boolean isEnd();
    boolean isEmpty();
    int size();
}
