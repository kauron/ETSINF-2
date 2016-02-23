package CSD;

public class RunnableExample implements Runnable{

    @Override
    public void run() {
        //Stop running this thread and free the CPU
        Thread.yield();

        //Example of sleep using Runnable
        try {
            Thread.sleep(1000); //time in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //A new thread is spawned using our Runnable object
        //containing the run() method
        new Thread(new RunnableExample()).start();
    }
}
