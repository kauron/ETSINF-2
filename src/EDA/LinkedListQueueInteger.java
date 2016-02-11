package EDA;

public class LinkedListQueueInteger extends LinkedListQueue<Integer> {
    /**
     * Removes the first element if and only if there are 2+ elements
     * and the sum of the rest of elements is smaller than the first element
     * @return Whether the operation was performed or not
     */
    public boolean deleteFirst() {
        if (first == last) return false;
        int sum = 0;
        for (Node<Integer> n = first.next; n != null && first.element > sum; n = n.next) {
            sum += n.element;
        }
        if (first.element <= sum) return false;
        first = first.next;
        first.previous = null;
        return true;
    }
}
