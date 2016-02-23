package CSD;

public class T2 extends Thread {
    private int level;

    public T2(int n) {level = n;}

    public void createThread(int i) {
        T2 h = new T2(i);
        if (i >= 1) h.start();
        System.out.printf("Thread of level %d created\n", i);
    }

    public void run() {
        if (level > 0) createThread(level - 1);
        System.out.println("End of thread. Level: " + level);
    }

    public static void main(String[] args){
        for(int i = 1; i < 3; i++) {
            new T2(2).start();
        }
    }
}