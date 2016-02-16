package CSD.ProducerConsumer;

public class Box {
    private int content = 0;
    private boolean full = false;

    public int get() {
        int value = content;
        content = 0;
        full = false;
        return value;
    }

    public void put(int value) {
        full = true;
        content = value;
    }
}
