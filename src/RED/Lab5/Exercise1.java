package RED.Lab5;

public class Exercise1 extends Thread {

    public static void main(String[] args) {
        MyThread1 t = new MyThread1();
        t.start();
        for (int i = 0; i < 10; i++) System.out.println(i);
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) System.out.println(i + "t");
    }
}