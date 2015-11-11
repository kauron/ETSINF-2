package RED.Lab5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Exercise3 {
    public static final int THREADS = 10;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7777);
        Thread.UncaughtExceptionHandler e =
                (t, e1) -> System.err.println("Uncaught exception: " + e1);
        EchoThread[] threads = new EchoThread[THREADS];
        while (!ss.isClosed()) {
            Socket s = ss.accept();
            EchoThread eThread = new EchoThread(s);
            eThread.start();
        }
    }
}

class EchoThread extends Thread {
    Socket s;
    static final String QUIT = "QUIT";

    EchoThread(Socket s) {this.s = s;}

    @Override
    public void run() {
        try {
            Scanner input = new Scanner(s.getInputStream());
            PrintWriter output = new PrintWriter(s.getOutputStream(), true);
            String echo;
            while (!(echo = input.nextLine()).equals(QUIT)) {
                output.println(echo);
                output.flush();
            }
            output.close();
            input.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}