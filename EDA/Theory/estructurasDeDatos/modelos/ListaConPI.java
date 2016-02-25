package estructurasDeDatos.modelos;

public interface ListaConPI<E> {
    void insertar(E e);
    void eliminar() throws Exception;
    E recuperar() throws Exception;

    void inicio();
    void fin();
    void siguiente();

    boolean esFin();
    boolean esVacia();
    int talla();
}
