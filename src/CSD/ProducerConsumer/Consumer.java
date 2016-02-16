package CSD.ProducerConsumer;

public class Consumer extends Thread{
    private Box box;
    private int cname;

    public Consumer(Box c, int name) {
        box = c; cname = name;
    }

    public void run() {
        int value = 0;
        for (int i = 1; i < 11; i++) {
            value = box.get();
            System.out.println("Consumer #" + cname + " gets: " + value);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
