package RED.Lab6;

import java.net.*;

public class Exercise3 {
    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket();
        System.out.println("DatagramSocket in local port: " + socket.getLocalPort());
    }
}
