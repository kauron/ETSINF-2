package EDA;

public interface ListaConPIExt<E> extends ListaConPI<E> {
    void search(E e);
    void vaciar();
    void reverse();
    int deleteAll(E x);
}
