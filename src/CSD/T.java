package CSD;

public class T extends Thread{
    protected int n;

    public T(int n) {this.n = n;}

    public void delay(int ms) {
        try {
            sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            new T(i).start();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread " + n + " iteration " + i);
            delay((1+n)*1000);
        }
        System.out.println("End thread " + n);
    }

}

class TRunnable implements Runnable{
    protected int n;

    public TRunnable(int n) {this.n = n;}

    public void delay(int ms) {
        try{
            Thread.sleep(ms);
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    public static void main (String[] args){
        for (int i = 0; i < 6; i++) {
            new Thread(new TRunnable(i)).start();
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Thread " + n + " iteration " + i);
            delay((n+1)*1000);
        }
        System.out.println("End thread " + n);
    }
}