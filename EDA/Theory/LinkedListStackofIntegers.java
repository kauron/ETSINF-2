package EDA;

public class LinkedListStackofIntegers extends LinkedListPila<Integer> {
    /**
     * Move the first node to the last position of the Pila.
     * Only moves the element if the stack has 2+ elements and
     * the first element is smaller than the last.
     * @return boolean Whether the move operation was performed or not
     */
    public boolean moveFirstToFinal() {
        if (esVacía()) return false;
        Nodo<Integer> first = top, aux = top;
        while (aux.next != null) aux = aux.next;
        if (first.dato >= aux.dato) return false;
        aux.next = first;
        first.next = null;
        top = top.next;
        return true;
    }
}