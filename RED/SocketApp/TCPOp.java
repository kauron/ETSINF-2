package RED.SocketApp;

import java.io.IOException;
import java.util.Scanner;

public class TCPOp {
    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);

        System.out.print("Server: ");
        String server = input.nextLine();
        System.out.print("Port: ");
        int port = input.nextInt();

        TCPClient tcp = new TCPClient();
        tcp.connect(server, port);
    }
}