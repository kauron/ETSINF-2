package estructurasDeDatos.lineales;

public class Nodo<E> {
    protected E dato;
    protected Nodo<E> previous, next;

    public Nodo(E dato) {this(dato, null);}
    public Nodo(Nodo<E> previous, E dato) {this(previous, dato, null);}
    public Nodo(E dato, Nodo<E> next) {this(null, dato, next);}
    public Nodo(Nodo<E> previous, E dato, Nodo<E> next) {
        this.previous = previous;
        this.dato = dato;
        this.next = next;
    }

    public E getDato() {return dato;}
    public void setDato(E dato) {this.dato = dato;}

    public Nodo<E> getNext() {return next;}
    public void setNext(Nodo<E> next) {this.next = next;}

    public Nodo<E> getPrevious() {return previous;}
    public void setPrevious(Nodo<E> previous) {this.previous = previous;}
}
