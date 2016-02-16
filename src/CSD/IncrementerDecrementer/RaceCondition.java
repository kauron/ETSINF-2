package CSD.IncrementerDecrementer;

public class RaceCondition {
    public static void main(String[] args) {
        Counter c = new Counter();
        int loops = 1000;
        System.out.println("Loops " + loops);

        Thread[] threads = new Thread[4];

        for (int i = 1; i <= 2; i++) {
            threads[i - 1] = new Incrementer(c, i, loops);
            threads[i + 1] = new Decrementer(c, i, loops);
        }

        for (Thread thread : threads) thread.start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final result: " + c.value());
    }
}
