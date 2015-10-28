package RED.Lab3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    static final String HOST = "www.upv.es";
    static final int PORT = 80;

    public static void main(String[] args) throws Exception {
        BufferedReader inUser = new BufferedReader(
                new InputStreamReader(System.in));
        Socket clientSocket = new Socket(HOST, PORT);
        DataOutputStream outServer = new DataOutputStream(
                clientSocket.getOutputStream());
        BufferedReader inServer = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        while(inUser.ready()) {
            outServer.writeBytes(inUser.readLine() + '\n');
            System.out.println("FROM SERVER: " + inServer.readLine());
        }
        clientSocket.close();
    }
}
