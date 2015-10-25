package LTP.Lab2.librerias.implementacionesDeModelos;

import LTP.Lab2.librerias.modelos.Cola;

import java.util.ArrayList;

public class ColaAL<T> implements Cola<T> {
    ArrayList<T> lista;
    int primero;

    public ColaAL() {
        lista = new ArrayList<>();
        primero = 0;
    }

    private int incrementar(int i) {
        return (i + 1) % talla();
    }

    @Override
    public void encolar(T e) {
        lista.add(e);
    }

    @Override
    public T desencolar() {
        T aux = lista.remove(primero);
        primero = incrementar(primero);
        return aux;
        //TODO: fix this method
    }

    @Override
    public int talla() {
        return lista.size();
    }

    @Override
    public T primero() {
        return lista.get(primero);
    }

    @Override
    public boolean esVacia() {
        return lista.isEmpty();
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = primero, c = 0; c < lista.size(); i = incrementar(i), c++)
            s += "<-" + lista.get(i);
        return s;
    }
}
