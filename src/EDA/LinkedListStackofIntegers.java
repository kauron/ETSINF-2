package EDA;

public class LinkedListStackofIntegers extends LinkedListStack<Integer> {
    /**
     * Move the first node to the last position of the Stack.
     * Only moves the element if the stack has 2+ elements and
     * the first element is smaller than the last.
     * @return boolean Whether the move operation was performed or not
     */
    public boolean moveFirstToFinal() {
        if (isEmpty()) return false;
        Node<Integer> first = top, aux = top;
        while (aux.next != null) aux = aux.next;
        if (first.element >= aux.element) return false;
        aux.next = first;
        first.next = null;
        top = top.next;
        return true;
    }
}