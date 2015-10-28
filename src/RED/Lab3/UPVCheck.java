package RED.Lab3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class UPVCheck {
    private static final String HOST = "www.upv.es";
    private static final int PORT = 80;

    public static void main(String[] args) throws IOException {
        UPVCheck upv = new UPVCheck();
//        System.out.println("Exercise 1:");
//        upv.ex1();
//        System.out.println("Exercise 2:");
//        upv.ex2();
//        System.out.println("Exercise 3:");
//        upv.ex3();
//        System.out.println("Exercise 4:");
//        upv.ex4();
//        System.out.println("Exercise 5:");
//        upv.ex5();
//        System.out.println("Exercise 6:");
//        upv.ex6();
        System.out.println("Exercise 7:");
        upv.ex7("kauron.ddns.net", 22);
    }

    private void ex1() throws IOException {
        Socket socket = new Socket(HOST, PORT);
        System.out.printf("Status: %s\n", socket.isConnected() ? "Connected!" : "ERROR");
        socket.close();
    }

    private void ex2() {
        try {
            ex1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ex3() throws IOException{
        connectAndPrint("smtp.upv.es", 25);
    }

    private void ex4() throws IOException{
        connectAndPrint("ntp.upv.es", 13);
    }

    private void ex5() throws IOException{
        final String MESSAGE = "GET HTTP/1.0\r\n\r\n";

        Socket socket = new Socket(HOST, PORT);

        DataOutputStream outServer = new DataOutputStream(
                socket.getOutputStream());

        Scanner inServer = new Scanner(socket.getInputStream());
        outServer.writeBytes(MESSAGE);
        outServer.flush();
        System.out.println(MESSAGE + "\nResponse from server:\n\n");

        while (inServer.hasNext())
            System.out.println(inServer.nextLine());

        inServer.close();
        outServer.close();
        socket.close();
    }

    private void connectAndPrint(String server, int port) throws IOException {
        Socket socket = new Socket(server, port);
        BufferedReader b = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        System.out.println(b.readLine());
        b.close();
        socket.close();
    }

    private void ex6() throws IOException {
        connectAndInfo("smtp.upv.es", 25);
    }

    private void connectAndInfo(String server, int port) throws IOException {
        Socket socket = new Socket(server, port);

        if(socket.isConnected()) {
            System.out.println("IP: "
                    + socket.getInetAddress().getHostAddress());
            System.out.println("REMOTE PORT: "
                    + socket.getPort());
            System.out.println("LOCAL PORT: "
                    + socket.getLocalPort());
        }

        socket.close();
    }

    private void ex7(String server, int port) throws IOException {
        connectAndInfo(server, port);
    }
}
