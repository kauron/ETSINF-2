package RED.Lab5;

public class Exercise2 {
    public static void main (String[] args) {
        MyThread2 t = new MyThread2("words");
        t.start();
        System.out.println("words1");
    }
}

class MyThread2 extends Thread{
    String word;

    MyThread2(String word) {this.word = word;}

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(word);
    }
}
