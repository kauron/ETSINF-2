package CSD;

public class ThreadExample extends Thread{
    @Override
    public void run() {
        //Here the code to execute concurrently

        //Stop running this thread and free the CPU
        yield();

        //Example of sleep in a Thread
        try {
            sleep(1000); //time in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //this.start will run concurrently
        //this.run will just execute run without creating a thread
        new ThreadExample().start();
    }
}
