package EDA;

public class LinkedListStackofIntegers extends LinkedListStack<Integer> {
    /**
     * Move the first node to the last position of the Stack.
     * Only moves the element if the stack has 2+ elements and
     * the first element is smaller than the last.
     * @return boolean Whether the move operation was performed or not
     */
    public boolean moveFirstToFinal() {
        if (isEmpty() || top.next == null) return false;
        Node<Integer> first = top, current = top;
        while (current.next != null) current = current.next;
        if (first.element >= current.element) return false;
        current.next = first;
        first.next = null;
        top = top.next;
        return true;
    }
}