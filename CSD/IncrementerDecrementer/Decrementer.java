package CSD.IncrementerDecrementer;

public class Decrementer extends Thread {
    private Counter c;
    private int myname, cycles;
    public Decrementer(Counter count, int name, int quantity) {
        c = count; myname = name; cycles = quantity;
    }

    public void run() {
        for (int i = 0; i < cycles; i++) {
            c.decrement();
            try {
                sleep((int) (Math.random() * 20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Decrementer #" + myname + " has done " + cycles + " decrements.");
    }
}
