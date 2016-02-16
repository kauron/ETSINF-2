package EDA;

public interface InterestPointListExt<E> extends InterestPointList<E> {
    void search(E e);
    void empty();
    void reverse();
    void erase(E e);
}
