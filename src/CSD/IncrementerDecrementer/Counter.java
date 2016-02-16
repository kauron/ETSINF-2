package CSD.IncrementerDecrementer;

public class Counter {
    private int c = 0;

    public synchronized void decrement() {c--;}
    public synchronized void increment() {c++;}

    public synchronized int value() {return c;}
}
