package RED.Lab5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Exercise3 extends Thread{
    public static final int THREADS = 10;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7777);
        while (!ss.isClosed()) (new Exercise3(ss.accept())).start();
    }

    Socket s;
    static final String QUIT = "QUIT";

    Exercise3(Socket s) {this.s = s;}

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