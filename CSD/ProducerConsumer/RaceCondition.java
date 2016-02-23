package CSD.ProducerConsumer;

public class RaceCondition {
    public static void main(String[] args) {
        Box c = new Box();
        Consumer c1 = new Consumer(c, 1);
        Producer p1 = new Producer(c, 1);

        c1.start(); p1.start();
    }
}
