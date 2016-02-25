package estructurasDeDatos.lineales;

public class LinkedListColaInteger extends LinkedListCola<Integer> {
    /**
     * Removes the first element if and only if there are 2+ elements
     * and the sum of the rest of elements is smaller than the first element
     * @return Whether the operation was performed or not
     */
    public boolean deleteFirst() {
        if (first == last) return false;
        int sum = 0;
        for (Nodo<Integer> n = first.next; n != null && first.dato > sum; n = n.next) {
            sum += n.dato;
        }
        if (first.dato <= sum) return false;
        first = first.next;
        first.previous = null;
        return true;
    }
}
