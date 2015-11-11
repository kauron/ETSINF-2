package RED.Lab5;

public class Exercise2 extends Thread{
    public static void main (String[] args) {
        (new Exercise2("words")).start();
        System.out.println("words1");
    }

    String word;

    public Exercise2 (String word) {this.word = word;}

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