package RED.Lab7;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;

public class Client extends Thread{
    Socket socket;

    public static void main(String[] args) throws IOException {
        Scanner kbd = new Scanner(System.in);
        System.out.print("Input server: ");
        String server = kbd.nextLine();
        System.out.print("Input port: ");
        int port = kbd.nextInt();
        Socket s = new Socket(server, port);
        Scanner in = new Scanner(s.getInputStream());
        if (s.isConnected()) System.out.println("Connection established!");
        (new Client(s)).start();
        while (s.isConnected())
            System.out.println(in.nextLine());
    }

    Client (Socket s) {socket = s;}

    @Override
    public void run() {
        try {
            Scanner kbd = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (socket.isConnected() && kbd.hasNextLine())
                out.println(kbd.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
