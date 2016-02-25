package librerias.estructurasDeDatos.modelos;

public interface Cola<E> {
    void queue(E element);
    E dequeue() throws Exception;
    E first() throws Exception;
    boolean isEmpty();
}
