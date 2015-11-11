package RED.Lab5;

public class Exercise1 extends Thread {

    public static void main(String[] args) {
        (new Exercise1()).start();
        for (int i = 0; i < 10; i++) System.out.println(i);
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) System.out.println(i + "t");
    }
}