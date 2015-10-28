package LTP.Lab2.librerias.implementacionesDeModelos;

import LTP.Lab2.librerias.modelos.Cola;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ColaAL<T> implements Cola<T> {
    ArrayList<T> lista;

    public ColaAL() {
        lista = new ArrayList<>();
    }

    @Override
    public void encolar(T e) {
        lista.add(e);
    }

    @Override
    public T desencolar() throws NoSuchElementException{
        if (esVacia()) throw new NoSuchElementException("There are no elements");
        return lista.remove(0);
    }

    @Override
    public int talla() {
        return lista.size();
    }

    @Override
    public T primero() throws NoSuchElementException{
        if (esVacia()) throw new NoSuchElementException("There are no elements");
        return lista.get(0);
    }

    @Override
    public boolean esVacia() {
        return lista.isEmpty();
    }

    @Override
    public String toString() {
        String s = "";
        for (T i : lista) s += "<-" + i;
        return s;
    }
}
