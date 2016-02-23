package RED.Lab6;

import java.io.IOException;
import java.net.*;

public class Exercise4 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] name = "Carlos Galindo Jiménez".getBytes();
        socket.send(new DatagramPacket(name, name.length,
                InetAddress.getByName("localhost"), 7777));
        // Second part of the exercise is to send it to "rdc00.redes.upv.es"
    }
    // Note that this is a UDP connection, only one packet can be sent
    // before the connection is closed, there is no connection
    // just packet exchange
}
