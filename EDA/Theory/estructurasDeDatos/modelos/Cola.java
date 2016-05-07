package estructurasDeDatos.modelos;

public interface Cola<E> {
    void encolar(E element);
    E desencolar() throws Exception;
    E primero() throws Exception;
    boolean esVacia();
}
